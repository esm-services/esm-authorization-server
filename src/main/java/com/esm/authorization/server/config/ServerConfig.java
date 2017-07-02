package com.esm.authorization.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class ServerConfig extends GlobalAuthenticationConfigurerAdapter {

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("durgesh").password("durgesh").roles("USER", "ADMIN", "OPERATOR").and()
		.withUser("vivek").password("vivek").roles("USER").and()
		.withUser("anup").password("anup").roles("OPERATOR");
	}

}
