package com.ok.securitydemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(req ->
						req.requestMatchers("/", "/home").permitAll()
						.requestMatchers("/public/**").hasRole("USER")
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated()
		).formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
						.username("user")
						.password("pwd")
						.roles("USER")
						.build();

		UserDetails admin = User.withDefaultPasswordEncoder()
						.username("admin")
						.password("pwd")
						.roles("ADMIN", "USER")
						.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
}
