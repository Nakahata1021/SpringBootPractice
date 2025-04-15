package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
				.loginProcessingUrl("/admin/signin")
				.loginPage("/admin/signin")
				.defaultSuccessUrl("/admin/contacts")
				.failureUrl("/admin/signin?error")
				.permitAll()
				.usernameParameter("email")
				.passwordParameter("password")
				).logout(logout -> logout
						.logoutUrl("/admin/logout")
						.logoutSuccessUrl("/admin/signup")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
						).authorizeHttpRequests(authz -> authz
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
								.requestMatchers("/admin/signin", "/admin/signup", "/contact/**").permitAll()
								.anyRequest().authenticated()
								);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
