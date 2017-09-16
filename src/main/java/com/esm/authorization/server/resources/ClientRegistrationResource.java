package com.esm.authorization.server.resources;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RefreshScope
@RestController
@AllArgsConstructor
public class ClientRegistrationResource {

	private final ClientRegistrationService clientRegistrationService;

	@PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HeadersBuilder<BodyBuilder> registerNewClient(@RequestBody BaseClientDetails clientDetails) {
		clientRegistrationService.addClientDetails(clientDetails);
		return ResponseEntity.ok();
	}
}
