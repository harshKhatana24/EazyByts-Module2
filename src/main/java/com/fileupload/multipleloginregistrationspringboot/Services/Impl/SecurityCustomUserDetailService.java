package com.fileupload.multipleloginregistrationspringboot.Services.Impl;

import com.fileupload.multipleloginregistrationspringboot.Config.CustomUserDetails;
import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import com.fileupload.multipleloginregistrationspringboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user from the database using email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Wrap the User entity into CustomUserDetails
        return new CustomUserDetails(user);
    }
}
