package com.example.springrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((httpz) ->
                httpz.antMatchers( /* HttpMethod.GET,*/ "/users/**").hasAnyRole("ROLE1", "ROLE2")
                     .antMatchers( HttpMethod.PUT,"/users/**").hasRole("ROLE2")
                     .antMatchers( HttpMethod.POST,"/users/**").hasRole("ROLE2")
                     .antMatchers( HttpMethod.DELETE,"/users/**").hasRole("ROLE2")

        ).httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager detailsManager() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("pass1")
                .roles("ROLE1")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("pass2")
                .roles("ROLE2")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
