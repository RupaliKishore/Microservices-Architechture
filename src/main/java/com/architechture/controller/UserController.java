package com.architechture.controller;

import com.architechture.dto.UserInput;
import com.architechture.model.User;
import com.architechture.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @QueryMapping
    public List<User> allUsers()
    {
        return this.userService.allUsers();
    }

    @QueryMapping
    public User getUser(@Argument Long id)
    {
        return this.userService.getUser(id);
    }

    @MutationMapping
    public User addUser(@Argument UserInput userInput)
    {
        return this.userService.addUser(userInput);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput userInput)
    {
        return this.userService.updateUser(id, userInput);
    }

    @MutationMapping
    public String deleteUser(@Argument Long id)
    {
        this.userService.deleteUser(id);
        return "User Deleted Successfully";
    }
}
