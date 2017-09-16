package com.esm.authorization.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthClientDetail {

	private String scope;

	private String client_id;

	private String autoapprove;

	private String authorities;
	
	private String resource_ids;

	private String client_secret;

	private int access_token_validity;

	private int refresh_token_validity;

	private String authorized_grant_types;

	private String web_server_redirect_uri;

	private String additional_information;
}
