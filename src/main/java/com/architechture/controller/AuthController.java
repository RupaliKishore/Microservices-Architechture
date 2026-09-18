package com.architechture.controller;

import com.architechture.dto.AuthInput;
import com.architechture.dto.UserInput;
import com.architechture.security.AuthService;
import com.architechture.model.AuthPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;


    @MutationMapping
    public AuthPayload register(@Argument UserInput userInput)
    {
        return authService.register(userInput);
    }

    @MutationMapping
    public AuthPayload login(@Argument AuthInput authInput)
    {
        return this.authService.login(authInput);
    }
}
