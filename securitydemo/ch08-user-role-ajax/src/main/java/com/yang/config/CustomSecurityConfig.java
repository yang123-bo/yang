package com.yang.config;

import com.yang.filter.VerificationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    //把SuccessHandler 和 FailureHandler注入
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;

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
                .antMatchers("/index","/myajax.html","/login","/js/**","/captcha/**").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")
                .antMatchers("/access/read/**").hasRole("READ")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .loginPage("/myajax.html")     //登录的自定义视图页面
                .loginProcessingUrl("/login")   //form中登录的访问uri地址
                .and()
                //关于跨域访问的安全设置，先禁用
                .csrf().disable();

        //在框架的过滤器链条中增加一个自定义过滤器
        http.addFilterBefore(new VerificationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
