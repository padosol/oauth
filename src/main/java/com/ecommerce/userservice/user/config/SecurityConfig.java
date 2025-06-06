package com.ecommerce.userservice.user.config;

import com.ecommerce.userservice.user.entity.Role;
import com.ecommerce.userservice.user.service.Oauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Oauth2UserService oauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))

            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

            .authorizeHttpRequests(
                authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/users/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 ->
                oauth2
                    .loginPage("/login")
                    .userInfoEndpoint(userInfoEndpointConfig ->
                        userInfoEndpointConfig.userService(oauth2UserService)
                    )
            )
        ;

        return http.build();
    }
}
