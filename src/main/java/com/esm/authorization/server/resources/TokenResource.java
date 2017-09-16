package com.esm.authorization.server.resources;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RefreshScope
@RestController
@AllArgsConstructor
public class TokenResource {

	private final TokenStore tokenStore;

	@RolesAllowed("ROLE_CLIENT")
	@GetMapping(value = "/token")
	public ResponseEntity<Collection<OAuth2AccessToken>> showClientTokens(OAuth2Authentication auth) {
		final String clientId = auth.getOAuth2Request().getClientId();
		final Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(clientId);
		return ResponseEntity.ok(tokensByClientId);
	}

	@RolesAllowed("ROLE_CLIENT")
	@DeleteMapping(value = "/token")
	public ResponseEntity<?> expireToken(OAuth2Authentication auth) {
		final OAuth2AccessToken accessToken = tokenStore.getAccessToken(auth);
		tokenStore.removeAccessToken(accessToken);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
