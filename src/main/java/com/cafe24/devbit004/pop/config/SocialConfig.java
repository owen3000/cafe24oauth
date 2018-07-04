package com.cafe24.devbit004.pop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.UUID;

@Configuration
public class SocialConfig extends SocialConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SocialConfig.class);

    @Override
    public UserIdSource getUserIdSource() {
        logger.debug("getUserIdSource called...");
        return new SessionIdUserIdSource();
    }

    private static final class SessionIdUserIdSource implements UserIdSource {
        private static final Logger logger = LoggerFactory.getLogger(SessionIdUserIdSource.class);

        @Override
        public String getUserId() {
            logger.debug("SocialConfig SessionIdUserIdSource getUserId called...");

            RequestAttributes request = RequestContextHolder.currentRequestAttributes();
            String uuid = (String) request.getAttribute("_socialUserUUID", RequestAttributes.SCOPE_SESSION);
            logger.debug("1. SocialConfig SessionIdUserIdSource uuid: " + uuid);

            if (uuid == null) {
                uuid = UUID.randomUUID().toString();
                logger.debug("2. SocialConfig SessionIdUserIdSource uuid: " + uuid);

                request.setAttribute("_socialUserUUID", uuid, RequestAttributes.SCOPE_SESSION);
            }
            return uuid;
        }
    }
}