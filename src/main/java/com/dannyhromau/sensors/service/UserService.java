package com.dannyhromau.sensors.service;

import com.dannyhromau.sensors.model.Role;
import com.dannyhromau.sensors.model.User;
import com.dannyhromau.sensors.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null){
            throw new UsernameNotFoundException("wrong password");
        }
        Collection<Role> roleCollection = new ArrayList<>();
        roleCollection.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), mapRolesToAuthorities(roleCollection));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getStatus())).collect(Collectors.toList());
    }

    public boolean saveUser(User user) {
        boolean isSaved = userRepository.findByLogin(user.getLogin()) == null;
        if (isSaved) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return isSaved;

    }
}

