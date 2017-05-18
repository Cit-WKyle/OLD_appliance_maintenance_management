package com.kyle.ie.auth.security.ajax;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.kyle.ie.auth.models.User;
import com.kyle.ie.auth.security.models.UserContext;
import com.kyle.ie.auth.services.IUserService;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
	
	private static final String NO_AUTH = "No authentication data provided";
	private static final String USER_NOT_FOUND = "User not found: ";
	private static final String AUTH_INVALID = "Authentication Failed. Username or password not valid.";
	private static final String NO_ROLES = "User has no roles assigned";
	
    private final BCryptPasswordEncoder _encoder;
    private final IUserService _userService;
    
    @Autowired
    public AjaxAuthenticationProvider(final IUserService userService, final BCryptPasswordEncoder encoder) {
        _userService = userService;
        _encoder = encoder;
    }

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, NO_AUTH);

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = _userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + username));
        
        if (!_encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(AUTH_INVALID);
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException(NO_ROLES);
        
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        
        UserContext userContext = UserContext.create(user.getUsername(), authorities, user.getUserType().getType());
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
