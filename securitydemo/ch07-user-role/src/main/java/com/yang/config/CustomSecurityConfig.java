package com.yang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("=======configure HttpSecurity==========");
        http.authorizeRequests()
                //指定可以直接访问的地址，和登录有关的要指定
                .antMatchers("/index","/mylogin.html","/login","/error.html").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")
                .antMatchers("/access/read/**").hasRole("READ")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .usernameParameter("myname")
//                .passwordParameter("mypwd")
                .loginPage("/mylogin.html")     //登录的自定义视图页面
                .loginProcessingUrl("/login")   //form中登录的访问uri地址
                .failureUrl("/error.html")
                .and()
                //关于跨域访问的安全设置，先禁用
                .csrf().disable();
    }



    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("=======configure HttpSecurity==========");
        http.authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")
                .antMatchers("/access/read/**").hasRole("READ")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }*/
}
