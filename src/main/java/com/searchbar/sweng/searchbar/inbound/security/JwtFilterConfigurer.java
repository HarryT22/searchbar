package com.searchbar.sweng.searchbar.inbound.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtValidator jwtValidator;

    public JwtFilterConfigurer(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    /**
     * Takes the personally configured filter from the JWT Filter class and requires the authentication for all methods that are not present in the filter.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(jwtValidator);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}