package com.architechture.service;

import com.architechture.dto.UserInput;
import com.architechture.model.User;

import java.util.List;

public interface UserService
{
    List<User> allUsers();

    User getUser(Long id);

    User addUser(UserInput userInput);

    User updateUser(Long id, UserInput userInput);

    void deleteUser(Long id);
}
