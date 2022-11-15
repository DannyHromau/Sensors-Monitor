package com.dannyhromau.sensors.dao;

import com.dannyhromau.sensors.config.UserCreationConfig;
import com.dannyhromau.sensors.model.Role;
import com.dannyhromau.sensors.model.Sensor;
import com.dannyhromau.sensors.model.User;
import com.dannyhromau.sensors.repositories.RoleRepository;
import com.dannyhromau.sensors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UsersManager {
    @Autowired
    private UserCreationConfig userCreationConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void createAndSaveUsers() {
        for (UserCreationConfig.UsersList usersList : userCreationConfig.getUsersList()){
            Role role = new Role();
            Role existsRole = roleRepository.findByStatus(usersList.getStatus());
            if (existsRole == null){
            role.setStatus(usersList.getStatus());
            roleRepository.save(role);}
            else {
                role = existsRole;
            }
            User user = new User(usersList.getLogin(), usersList.getPassword(), role);
            if (!userService.saveUser(user)){
                System.out.println("Failed! User "  +user.getLogin() +  " already exists");
            }
        }

    }
}
