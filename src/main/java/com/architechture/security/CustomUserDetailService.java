package com.architechture.security;

import com.architechture.repository.UserRepository;
import com.architechture.model.User;                       // ← your entity
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Build Spring Security's UserDetails with a fully-qualified name
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())        // ← NOT getUsername() (your entity has no such method)
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}