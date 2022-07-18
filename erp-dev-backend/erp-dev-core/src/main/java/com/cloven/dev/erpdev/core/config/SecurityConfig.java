package com.cloven.dev.erpdev.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloven.dev.erpdev.core.filter.JwtAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//private final ResourceServerProperties resource;

	private static final String[] AUTH_WHITELIST = {

			"/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/user-authenticate/login",
			"/user-authenticate/sign-up", "/actuator/**" };


	@Autowired
	private CorsFilterConfig corsFilterConfig;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEntryPoint;

	@Bean
    public JwtAuthFilter authTokenFilterBean() throws Exception {
        return new JwtAuthFilter();
    }

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest()
				.permitAll().and().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.headers().frameOptions().sameOrigin();
		httpSecurity.cors();
		httpSecurity.httpBasic().disable();
		httpSecurity.addFilterBefore(corsFilterConfig, ChannelProcessingFilter.class);
		httpSecurity.addFilterAfter(authTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}



}