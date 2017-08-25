package com.esm.authorization.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ESMAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ESMAuthorizationServerApplication.class, args);
	}
}
