package com.music.app.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private String roles;
    // @Column(nullable = false)
    // private boolean emailVerified;

    // Default constructor is needed for JPA
}
// INSERT INTO my_user (id, username, password, roles) VALUES 
// (1000, 'Jhon', '$2a$12$JRP8vS.dBHiGYBAkT69K9.UEt/F1P09jYVhUAAfwTJMhiKK8HjLxu', 'USER'),
// (1001, 'Jane', '$2a$12$JRP8vS.dBHiGYBAkT69K9.UEt/F1P09jYVhUAAfwTJMhiKK8HjLxu', 'ADMIN,USER');
// INSERT 0 2
