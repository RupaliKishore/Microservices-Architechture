package com.architechture.dto;

import com.architechture.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInput
{
    private String name;

    private String email;

    private String password;

    private String address;

    private UserRole role;
}
