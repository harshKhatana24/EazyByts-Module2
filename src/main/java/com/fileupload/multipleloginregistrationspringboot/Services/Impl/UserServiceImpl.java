package com.fileupload.multipleloginregistrationspringboot.Services.Impl;

import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import com.fileupload.multipleloginregistrationspringboot.Repository.UserRepository;
import com.fileupload.multipleloginregistrationspringboot.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    @Override
    public boolean userExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
