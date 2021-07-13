package com.codechallenge.shopapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

    /**
     * Security Config
     */
    @Configuration
    @EnableWebMvc
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        /**
         * Set up what happens for different urls
         *
         * @param http security context
         * @throws Exception possible exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.csrf().disable() // disable csrf for our requests.
                    .authorizeRequests()
                    .antMatchers("/shop/products/breakdown").permitAll()
                    .antMatchers("/shop/orders/**").permitAll()
                    .antMatchers("/shop/orders/create").permitAll()
                    .anyRequest().authenticated();
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/health").allowedOrigins("http://localhost:8080");
                }
            };
        }
    }
