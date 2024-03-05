package com.taxi.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import com.taxi.app.config.handlers.LoginSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * SecurityConfig
 *
 * @author alankavanagh
 *
 * Security Configuration used for Spring Boot Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final LoginSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        logger.debug("SecurityConfig: Executing bCryptPasswordEncoder()");

        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SecurityConfig(final LoginSuccessHandler authenticationSuccessHandler) {
        logger.debug("SecurityConfig: Constructing SecurityConfig");

        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("SecurityConfig: Executing configureGlobal()");

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        logger.debug("SecurityConfig: Executing authenticationManagerBean()");

        return super.authenticationManagerBean();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        logger.debug("SecurityConfig: Executing securityDialect()");

        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        logger.debug("SecurityConfig: Executing configure()");

        /*
         * Resources: /css, /images, /bootstrap - permit all
         * Webpages: /login, /logout, /register - permit all
         * Webpages: /, /landing, /displayBookings, /displayTaxis, /insertBooking - require authentication
         * Webpages: /swagger-ui.html, /h2-console - require authentication
         */
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers( "/css/**", "/images/**").permitAll()
                .antMatchers("/", "/landing", "displayBookings", "displayTaxis", "insertBooking").authenticated()
                .and().authorizeRequests().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagger-ui.html").permitAll().anyRequest().authenticated()
                .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().headers().frameOptions().disable()
                .and().csrf().disable();
    }
}