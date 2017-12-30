package com.esm.authorization.server.security;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.esm.authorization.server.domain.Role;
import com.esm.authorization.server.domain.User;
import com.esm.authorization.server.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@AllArgsConstructor
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	private final UserService userService;

	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Inside GlobalAuthenticationConfig Class =>");
		if (userService.count() == 0) {
			log.info("There is no user exist, creating a dumy user");
			Set<Role> role = new HashSet<Role>(asList(new Role("ACTUATOR")));
			User user = new User("user", "password", true, role);
			userService.createNewUser(user);
			log.info("Dumy user ceated successfully with username '{}' and password '{}'", user.getUsername(), user.getPassword());
		}
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
		log.info("GlobalAuthentication completed successfully =>");
	}
}
