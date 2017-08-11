package com.esm.authorization.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.esm.authorization.server.domain.User;

public interface UserService extends UserDetailsService {

	public User findUserByUsername(String username);

	public void createNewUser(User user);
	
	public long count();
}
