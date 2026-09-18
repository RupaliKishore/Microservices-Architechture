package com.architechture.service.implementation;

import com.architechture.dto.UserInput;
import com.architechture.exception.ResourceArgumentException;
import com.architechture.model.User;
import com.architechture.model.UserRole;
import com.architechture.repository.UserRepository;
import com.architechture.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResourceArgumentException("User", "id", id));
    }

    @Override
    public User addUser(UserInput userInput)
    {
        User user = new User();
        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setAddress(userInput.getAddress());
        user.setRole(userInput.getRole() != null ? userInput.getRole() : UserRole.USER_ROLE);
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserInput userInput)
    {
        User existuser = userRepository.findById(id).orElseThrow(() -> new ResourceArgumentException("User", "id", id));
        existuser.setName(userInput.getName());
        existuser.setEmail(userInput.getEmail());
        existuser.setPassword(passwordEncoder.encode(userInput.getPassword()));
        existuser.setAddress(userInput.getAddress());
        this.userRepository.save(existuser);
        return existuser;
    }

    @Override
    public void deleteUser(Long id)
    {
        User existUser =  this.userRepository.findById(id).orElseThrow(() -> new ResourceArgumentException("User", "id", id));
         this.userRepository.delete(existUser);
    }
}
