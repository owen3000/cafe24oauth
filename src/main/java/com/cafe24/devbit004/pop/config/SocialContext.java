package com.cafe24.devbit004.pop.config;

import com.cafe24.devbit004.pop.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.cafe24.api.Cafe24;
import org.springframework.social.cafe24.config.util.Cafe24Interceptor;
import org.springframework.social.cafe24.config.util.ProviderUserIdConnectionSignUp;
import org.springframework.social.cafe24.config.util.UserCookieSignInAdapter;
import org.springframework.social.cafe24.connect.Cafe24ConnectionFactory;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableSocial
@EnableJpaRepositories
public class SocialContext implements SocialConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(SocialContext.class);

    @Autowired
    private DataSource dataSource;

    private static JdbcUsersConnectionRepository jdbcUsersConnectionRepository;


    @Bean
    public Cafe24ConnectionFactory cafe24ConnectionFactory(Environment env){
        Cafe24ConnectionFactory cafe24ConnectionFactory = new Cafe24ConnectionFactory(
                env.getProperty("cafe24.app.id"),
                env.getProperty("cafe24.app.secret"),
                env.getProperty("cafe24.redirect.uri"),
                env.getProperty("cafe24.scope")
        );
        return cafe24ConnectionFactory;
    }

    /**
     * Configures the connection factories for Facebook and Twitter.
     * @param cfConfig
     * @param env
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        logger.info("addConnectionFactories started..." );
        cfConfig.addConnectionFactory(cafe24ConnectionFactory(env));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new SocialConfig().getUserIdSource();
    }



    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
    public Cafe24 cafe24(ConnectionRepository repository) {
        logger.info("cafe24 bean called...");
        Connection<Cafe24> connection = repository.findPrimaryConnection(Cafe24.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    @Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        logger.info("connectionRepository called...");
        return jdbcUsersConnectionRepository.createConnectionRepository(getUserIdSource().getUserId());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        logger.info("getUsersConnectionRepository started..." );

        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
        jdbcUsersConnectionRepository = repository;
        repository.setConnectionSignUp(new ProviderUserIdConnectionSignUp());

        return repository;
    }

    /**
     * This bean manages the connection flow between the account provider and
     * the example application.
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        ConnectController connectController = new LoginController(connectionFactoryLocator, connectionRepository);
        connectController.addInterceptor(new Cafe24Interceptor());

        return connectController;
    }

    /**
     * The Spring MVC Controller that allows users to sign-in with their provider accounts.
     */
    @Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator,
                                                             UsersConnectionRepository usersConnectionRepository) {
        ProviderSignInController providerSignInController
                = new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository,
                new UserCookieSignInAdapter());
        providerSignInController.setPostSignInUrl("/connect2/result");
        return providerSignInController;
    }



}
