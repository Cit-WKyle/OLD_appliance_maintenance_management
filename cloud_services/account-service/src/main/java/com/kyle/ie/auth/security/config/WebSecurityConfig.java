package com.kyle.ie.auth.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyle.ie.auth.rest.RestAuthenticationEntryPoint;
import com.kyle.ie.auth.security.ajax.AjaxAuthenticationProvider;
import com.kyle.ie.auth.security.ajax.AjaxLoginProcessingFilter;
import com.kyle.ie.auth.security.jwt.JwtAuthenticationProvider;
import com.kyle.ie.auth.security.jwt.JwtTokenAuthenticationProcessingFilter;
import com.kyle.ie.auth.security.jwt.SkipPathRequestMatcher;
import com.kyle.ie.auth.security.jwt.extractor.ITokenExtractor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/ajax/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/token";
    public static final String REST_LOGIN_ENTRY_POINT = "/login";
    public static final String REST_REG_ENTRY_POINT = "register";
    
    @Autowired
    private RestAuthenticationEntryPoint _authenticationEntryPoint;
    
    @Autowired
    private AuthenticationSuccessHandler _successHandler;
    
    @Autowired
    private AuthenticationFailureHandler _failureHandler;
    
    @Autowired
    private AjaxAuthenticationProvider _ajaxAuthenticationProvider;
    
    @Autowired
    private JwtAuthenticationProvider _jwtAuthenticationProvider;
    
    @Autowired
    private ITokenExtractor _tokenExtractor;
    
    @Autowired
    private AuthenticationManager _authManager;
    
    @Autowired
    private ObjectMapper _objectMapper;
    
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, _successHandler, _failureHandler, _objectMapper);
        filter.setAuthenticationManager(_authManager);
        return filter;
    }
    
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT, REST_LOGIN_ENTRY_POINT, REST_REG_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter 
            = new JwtTokenAuthenticationProcessingFilter(_failureHandler, _tokenExtractor, matcher);
        filter.setAuthenticationManager(_authManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(_ajaxAuthenticationProvider);
        auth.authenticationProvider(_jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(_authenticationEntryPoint)
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()
                .antMatchers(REST_LOGIN_ENTRY_POINT).permitAll()
        .and()
            .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
        .and()
            .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
