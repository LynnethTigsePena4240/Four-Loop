package com.example.FourLoop.config;

import com.example.FourLoop.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo){

        return username -> {
            com.example.FourLoop.Model.User user = repo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));

            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF disabled globally — ignoringRequestMatchers not needed
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login").permitAll()
                        
                        // admin only
                        .requestMatchers("/delete-product/**", "/admin/**").hasRole("ADMIN")
                        // admin and user can view/update inventory
                        .requestMatchers("/", "/products", "/products/**", "/inventory", "/inventory/**", "/inventory-update", "/about")
                        .hasAnyRole("ADMIN", "USER")
                        // everything else only admin   
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/login?logout")
                                        .permitAll()
                        )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                        );

        return http.build();
    }


}
