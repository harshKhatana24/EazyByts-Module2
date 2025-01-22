package com.fileupload.multipleloginregistrationspringboot.Config;

import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    // Constructor to inject the user
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assign role to user as authority
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        // Return hashed password stored in the database
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Return email for username
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Account is always non-expired for now
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Account is always non-locked for now
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Credentials are always non-expired for now
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Return the user status
        return true;
    }
}
