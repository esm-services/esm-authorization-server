package com.esm.authorization.server.resources;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esm.authorization.server.domain.User;
import com.esm.authorization.server.services.UserService;

import lombok.AllArgsConstructor;

@RefreshScope
@RestController
@AllArgsConstructor
public class UserResource {

	private final UserService userService;

	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("#oauth2.hasScope('write') and hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<User> createNewUser(@RequestBody User user) {
		User data = userService.createNewUser(user);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

}
