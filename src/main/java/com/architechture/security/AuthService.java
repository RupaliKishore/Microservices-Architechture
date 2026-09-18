package com.architechture.security;

import com.architechture.dto.AuthInput;
import com.architechture.dto.UserInput;
import com.architechture.model.AuthPayload;
import com.architechture.model.User;
import com.architechture.model.UserRole;
import com.architechture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthPayload register(UserInput userInput) {
        if (userRepository.existsByEmail(userInput.getEmail())) throw new RuntimeException("Email already exists");

        User user = new User();
        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setAddress(userInput.getAddress());
        user.setRole(UserRole.USER_ROLE);
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthPayload(token, user);

    }

    public AuthPayload login(AuthInput authInput) {
        // throws BadCredentialsException if wrong
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authInput.getEmail(), authInput.getPassword()));

        User user = userRepository.findByEmail(authInput.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthPayload(token, user);
    }
}
