package com.marcskow.springserver.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@Order(2)
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**").authorizeRequests().anyRequest().authenticated();

//                anonymous().disable()
//                .requestMatchers().antMatchers("/user/**", "/api/**")
             //   .and().authorizeRequests()
//                .antMatchers("/user/**").access("hasRole('ADMIN')")
                //.antMatchers("/api/**").access("hasRole('USER')")
               // .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}