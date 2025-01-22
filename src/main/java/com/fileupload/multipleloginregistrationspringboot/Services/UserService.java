package com.fileupload.multipleloginregistrationspringboot.Services;


import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    public User createUser(User user);

    public boolean userExist(String email);

}
