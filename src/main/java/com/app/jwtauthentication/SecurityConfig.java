package com.app.jwtauthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	// @Autowired
	private PasswordEncoder enc;

	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Autowired
	private CustomAuthenticationEntryPoint authEntry;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		// URL based authorization rules
		http.cors().and().
		// disable CSRF token generation n verification
				csrf().disable().exceptionHandling().authenticationEntryPoint(authEntry).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/employee/{Id}").hasRole("USER").antMatchers(HttpMethod.GET, "/employee")
				.hasRole("ADMIN").antMatchers(HttpMethod.POST, "/employee").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/employee/{Id}").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/employee/{Id}").hasRole("ADMIN").anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
