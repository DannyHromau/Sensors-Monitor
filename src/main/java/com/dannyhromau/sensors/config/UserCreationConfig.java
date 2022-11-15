package com.dannyhromau.sensors.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "users")
public class UserCreationConfig {
    private List<UsersList> usersList;
    @Data
    public static class UsersList {
        private String login;
        private String password;
        private String status;
    }
}
