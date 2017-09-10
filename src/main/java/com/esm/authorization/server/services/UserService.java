package com.esm.authorization.server.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.esm.authorization.server.domain.User;

public interface UserService extends UserDetailsService {

	public User findUserByUsername(String username);

	public User createNewUser(User user);
	
	public long count();
}
