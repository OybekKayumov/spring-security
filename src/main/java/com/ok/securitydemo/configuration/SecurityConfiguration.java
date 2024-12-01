package com.ok.securitydemo.configuration;

import com.ok.securitydemo.repository.MyUser;
import com.ok.securitydemo.repository.MyUserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(req ->
						req.requestMatchers("/", "/home").permitAll()
						.requestMatchers("/public/**").hasRole("USER")
						.requestMatchers("/admin/**").hasRole("ADMIN")
						//.anyRequest().authenticated()
		).formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
						.username("user")
						//.password("pwd")
						.password(passwordEncoder().encode("user"))
						.roles("USER")
						.build();

		UserDetails admin = User.builder()
						.username("admin")
						//.password("pwd")
						.password(passwordEncoder().encode("admin"))
						.roles("ADMIN", "USER")
						.build();

		System.out.println("pwd encoder user: " + passwordEncoder().encode("user"));
		System.out.println("pwd encoder admin: " + passwordEncoder().encode(
						"admin"));
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner loadInitialUsersInDB(MyUserRepo repo ) {
		return args -> {
			MyUser user1 = new MyUser();
			user1.setUsername("user");
			user1.setPassword(passwordEncoder().encode("user"));
			user1.setRoles(Set.of("USER"));
			repo.save(user1);

			MyUser user2 = new MyUser();
			user2.setUsername("admin");
			user2.setPassword(passwordEncoder().encode("admin"));
			user2.setRoles(Set.of("ADMIN", "USER"));
			repo.save(user2);

			MyUser user3 = new MyUser();
			user3.setUsername("adminsp");
			user3.setPassword(passwordEncoder().encode("adminsp"));
			user3.setRoles(Set.of("ADMINSP"));
			repo.save(user3);

			System.out.println("Users saved in DB...");
		};
	}

}
