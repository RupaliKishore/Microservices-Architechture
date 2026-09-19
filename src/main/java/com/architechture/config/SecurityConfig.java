package com.architechture.config;


import com.architechture.security.JwtAutFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    private final JwtAutFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .logout(logout -> logout.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/graphql",         // API (POST)
                        "/graphql/**",
                        "/graphiql",        // UI page ✅
                        "/graphiql/**",     // UI static assets ✅
                        "/favicon.ico",
                        "/error").permitAll().anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
