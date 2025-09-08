package org.project.frogs.frogs_project_backoffice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/frogs/create", "/frogs/edit").hasAnyAuthority("ADMIN")
                .requestMatchers("/habitats/create", "/habitats/edit").hasAnyAuthority("ADMIN")
                .requestMatchers("/conservationStatuses/create", "/conservationStatuses/edit").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/frogs/**", "/habitats/**", "/conservationStatuses/**")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/frogs/**", "/habitats/**", "/conservationStatuses/**")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/frogs/**", "/habitats/**", "/conservationStatuses/**")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/frogs/**", "/habitats/**", "/conservationStatuses/**")
                .hasAuthority("ADMIN")
                .requestMatchers("/frogs", "frogs/**", "/habitats", "/habitats/**", "conservationStatuses",
                        "/conservationStatuses/**")
                .hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/login", "/logout").permitAll()
                .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
