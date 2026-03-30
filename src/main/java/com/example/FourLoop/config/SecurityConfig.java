package com.example.FourLoop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.FourLoop.Repository.UserRepository;

@Configuration
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(UserRepository repo) {
                return username -> {
                        com.example.FourLoop.Model.User user = repo.findByUsername(username)
                                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
                        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                                        .password(user.getPassword())
                                        .roles(user.getRole())
                                        .build();
                };
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/about", "/login", "/register", "/error")
                                                .permitAll()
                                                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**")
                                                .permitAll()

                                                .requestMatchers("/products/**").hasAnyRole("USER", "STAFF", "ADMIN")

                                                .requestMatchers("/inventory").hasAnyRole("STAFF", "ADMIN")

                                                .requestMatchers("/inventory-update/**", "/delete-product/**",
                                                                "/admin/**")
                                                .hasRole("ADMIN")

                                                .anyRequest().authenticated())

                                .exceptionHandling(exception -> exception
                                                .accessDeniedPage("/error"))

                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll())

                                .logout(logout -> logout
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll())

                                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

                return http.build();
        }
}