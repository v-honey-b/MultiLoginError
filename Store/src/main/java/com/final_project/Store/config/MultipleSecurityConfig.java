package com.final_project.Store.config;

import com.final_project.Store.service.MemberService;
import com.final_project.Store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MultipleSecurityConfig {

    @Autowired
    StoreService storeService;

    @Autowired
    MemberService memberService;

    @Order(1)
    @Configuration
    public static class StoreConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.formLogin()
                    .loginPage("/stores/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("storeId")
                    .passwordParameter("storePwd")
                    .failureUrl("/stores/login/error")
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/stores/logout"))
                    .logoutSuccessUrl("/");
        }
    }

    @Order(2)
    @Configuration
    public static class MemberCongig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.formLogin()
                    .loginPage("/members/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("memberId")
                    .passwordParameter("memberPwd")
                    .failureUrl("/members/login/error")
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                    .logoutSuccessUrl("/");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(storeService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
