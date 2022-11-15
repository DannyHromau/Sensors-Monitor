package com.dannyhromau.sensors.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    @ManyToOne
    @JoinColumn(name="roles_id", nullable=false)
    private Role role;

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
