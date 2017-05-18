package com.kyle.ie.auth.controllers.rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.ie.auth.exceptions.InvalidJwtToken;
import com.kyle.ie.auth.forms.RegisterForm;
import com.kyle.ie.auth.json.JSONErrorGenerator;
import com.kyle.ie.auth.models.AuthUserPayload;
import com.kyle.ie.auth.models.User;
import com.kyle.ie.auth.models.UserDetails;
import com.kyle.ie.auth.security.JwtAuthenticationToken;
import com.kyle.ie.auth.security.config.JwtSettings;
import com.kyle.ie.auth.security.config.WebSecurityConfig;
import com.kyle.ie.auth.security.jwt.extractor.ITokenExtractor;
import com.kyle.ie.auth.security.jwt.verifier.ITokenVerifier;
import com.kyle.ie.auth.security.models.UserContext;
import com.kyle.ie.auth.security.models.token.IJwtToken;
import com.kyle.ie.auth.security.models.token.JwtTokenFactory;
import com.kyle.ie.auth.security.models.token.RawAccessJwtToken;
import com.kyle.ie.auth.security.models.token.RefreshToken;
import com.kyle.ie.auth.services.IUserService;
import com.kyle.ie.auth.validation.ILoginValidator;
import com.kyle.ie.auth.validation.IRegistrationValidator;

@RestController
@RequestMapping(value="/")
public class AuthRestController {
	
	private static final String USER_NOT_FOUND = "User not found: ";
	private static final String USER_NO_ROLES = "User has no roles assigned";
	private static final String AUTH_INVALID = "Authentication Failed. Username or password not valid.";
	private static final String TOKEN_AUTH_INVALID = "Authentication Failed. Invalid token";
	
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";
	
	private static final String TOKEN_KEY = "token";
	
    @Autowired 
    private JwtTokenFactory _tokenFactory;
    
    @Autowired 
    private JwtSettings _jwtSettings;
    
    @Autowired 
    private IUserService _userService;
    
    @Autowired 
    private ITokenVerifier _tokenVerifier;
    
    @Autowired
    private ILoginValidator _loginValidator;
    
    @Autowired 
    @Qualifier("jwtHeaderTokenExtractor") 
    private ITokenExtractor tokenExtractor;
    
    @Autowired
    private IRegistrationValidator _regValidator;
    
    @RequestMapping(value="/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody IJwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, _jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!_tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = _userService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + subject));

        if (user.getRoles() == null) throw new InsufficientAuthenticationException(USER_NO_ROLES);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities, user.getUserType().getType());

        return _tokenFactory.createAccessJwtToken(userContext);
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public @ResponseBody String register(@RequestBody RegisterForm form) {
    	boolean valid = _regValidator.validate(form);
    	
    	if(!valid) {
    		return new JSONErrorGenerator().generate(_regValidator.getErrors()).toString();
    	}
    
    	JSONObject obj = new JSONObject();
    	
    	User user = _userService.register(form);
    	
        if (user.getRoles() == null) throw new InsufficientAuthenticationException(USER_NO_ROLES);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities, user.getUserType().getType());
        obj.put(TOKEN_KEY, _tokenFactory.createAccessJwtToken(userContext));
    	return obj.toString();
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public @ResponseBody IJwtToken login(@RequestBody String jsonStr) {
    	JSONObject json = new JSONObject(jsonStr);
    	String username = json.getString(USERNAME_KEY);
    	String password = json.getString(PASSWORD_KEY);

        User user = _userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + username));
        
        if(!_loginValidator.validate(user, password)) {
            throw new BadCredentialsException(AUTH_INVALID);
        }
        
        if (user.getRoles() == null) throw new InsufficientAuthenticationException(USER_NO_ROLES);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities, user.getUserType().getType());
        return _tokenFactory.createAccessJwtToken(userContext);
    }


	@RequestMapping(value="/user", method=RequestMethod.GET)
    public @ResponseBody UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
	
	@RequestMapping(value="/user/id", method=RequestMethod.GET)
	public @ResponseBody AuthUserPayload auth(JwtAuthenticationToken token) {
		UserContext context = (UserContext) token.getPrincipal();
        User user = _userService.getByUsername(context.getUsername()).orElse(null);
        long id = 0;
        boolean success = false;
        String type = "";
        String error = TOKEN_AUTH_INVALID;
        if(user != null) {
        	id = user.getId();
        	type = user.getUserType().getType();
        	success = true;
        	error = "";
        }
        return new AuthUserPayload(id, type, success, error);
	}
	
	@RequestMapping(value="/user/details", method=RequestMethod.GET)
	public @ResponseBody UserDetails getDetails(JwtAuthenticationToken token) {
		UserContext context = (UserContext) token.getPrincipal();
        User user = _userService.getByUsername(context.getUsername()).orElse(null);
        
        return new UserDetails(user.getId(), user.getEmail(), user.getUsername(), user.getUserType().getType());
	}
}
