package com.esm.authorization.server.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.esm.authorization.server.domain.Role;
import com.esm.authorization.server.domain.User;
import com.esm.authorization.server.services.UserService;

@Configuration
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	private final UserService userService;

	private final BCryptPasswordEncoder passwordEncoder;

	public GlobalAuthenticationConfig(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		if (userService.count() == 0) {
			Set<Role> role = new HashSet<Role>(Arrays.asList(new Role("ACTUATOR")));
			User user = new User("user", "password", true, role);
			userService.createNewUser(user);
		}
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
}
