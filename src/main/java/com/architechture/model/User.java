package com.architechture.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    // autofill timestamp on insert
    @PrePersist
    void onCreate()
    {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;

        if(this.role == null)
        {
            this.role = UserRole.USER_ROLE;
        }
    }


    // autofill timestamp update
    @PreUpdate
    void onUpdate()
    {
        this.updatedAt = LocalDateTime.now();
    }


}
