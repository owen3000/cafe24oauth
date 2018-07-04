package org.springframework.social.cafe24.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.cafe24.api.Cafe24;
import org.springframework.social.cafe24.connect.Cafe24ConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class Cafe24AuthenticationService extends OAuth2AuthenticationService<Cafe24> {
	private static final Logger logger = LoggerFactory.getLogger(Cafe24AuthenticationService.class);
	static {
		logger.info("Cafe24AuthenticationService started");
	}
	public Cafe24AuthenticationService(String appId, String appSecret, String redirectUri, String scope) {
		super(new Cafe24ConnectionFactory(appId, appSecret, redirectUri, scope));
	}

	private static void testAuthentication() {

	}
}
