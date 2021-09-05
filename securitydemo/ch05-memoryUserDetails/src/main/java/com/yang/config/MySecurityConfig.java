package com.yang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService detailsService = null;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.userDetailsService(detailsService);
    }
}
