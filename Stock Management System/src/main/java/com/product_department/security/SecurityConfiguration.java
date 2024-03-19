package com.product_department.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private OwnUserDetailsManager userDetailsManager;

    public SecurityConfiguration(OwnUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        String [] authEndPoints = {"/api/add-department-products","/api/add-newproduct","/api/delete-department"
                                    ,"/api/update-product" , "/api/deleteby-name"};
        return httpSecurity.csrf(config-> config.disable())
                    .authorizeHttpRequests(auth-> {
                            auth.requestMatchers("error"  ).permitAll();
                            auth.requestMatchers(HttpMethod.GET).permitAll();
                            auth.requestMatchers("/create-account").permitAll();
                            auth.requestMatchers(authEndPoints)
                                    .hasAuthority("admin").anyRequest().authenticated();

                               } ).httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsManager)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

}
