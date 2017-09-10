package com.esm.authorization.server.resources;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityResource {

	private static final Logger logger = LoggerFactory.getLogger(SecurityResource.class);

	@GetMapping("/user")
	public Principal user(Principal principal) {
		logger.info("AS / user has been called");
		logger.debug("user info: " + principal.toString());
		return principal;
	}
}
