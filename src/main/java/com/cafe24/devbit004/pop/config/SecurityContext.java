package com.cafe24.devbit004.pop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableWebSecurity
@EnableJpaRepositories
public class SecurityContext extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                    .antMatchers("/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Configures form login
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login/authenticate")
                    .failureUrl("/login?error=bad_credentials")
                //Configures the logout function
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                //Configures url based authorization
                .and()
                    .authorizeRequests()
                        //Anyone can access the urls
                        .antMatchers(
                                "/",
                                "/auth/**",
                                "/login",
                                "/signup/**",
                                "/user/register/**",
                                "/test/**",
                                "/test2/**",
                                "/connect/**",
                                "/connect2/**"
                        ).permitAll()
                        //The rest of the our application is protected.
                        .antMatchers("/admin").hasRole("USER")
                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                .and()
                    /*.apply(new SpringSocialConfigurer())
                .and()*/.csrf().disable();
//                .addFilter(new SocialAuthenticationFilter( ));
    }

    /**
     * Configures the authentication manager bean which processes authentication
     * requests.
     */
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    *//**
     * This is used to hash the password of the user.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /*@Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        return new SocialAuthenticationServiceRegistry().setAuthenticationServices(new Cafe24AuthenticationService("appId", "appSecret"));
    }*/
    /**
     * This bean is used to load the user specific data when social sign in
     * is used.
     */
    /*@Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailsService(userDetailsService());
    }*/

    /**
     * This bean is load the user specific data when form login is used.
     */
   /* @Bean
    public UserDetailsService userDetailsService() {
        return new RepositoryUserDetailsService(userRepository);
    }*/
}
