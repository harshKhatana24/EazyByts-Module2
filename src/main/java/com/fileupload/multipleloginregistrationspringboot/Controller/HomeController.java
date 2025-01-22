package com.fileupload.multipleloginregistrationspringboot.Controller;

import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import com.fileupload.multipleloginregistrationspringboot.Helper.AppConstants;
import com.fileupload.multipleloginregistrationspringboot.Repository.UserRepository;
import com.fileupload.multipleloginregistrationspringboot.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("/")
    private String index() {
        return "index";
    }

    @GetMapping("/register")
    private String register() {
        return "register";
    }

    @GetMapping("/signin")
    private String login() {
        return "login";
    }

    @PostMapping("/createUser")
    private String registerUser(@ModelAttribute User user, HttpSession session) {
        // Check if user exists by email
        boolean ifExists = userService.userExist(user.getEmail());

        if (ifExists) {
            session.setAttribute("msg", "Email already Exists");
            return "redirect:/register";
        } else {
            // Role is already set in the form, no need for extra processing here
            User user1 = userService.createUser(user);
            if (user1 != null) {
                session.setAttribute("msg", "Registration Successful!");
            } else {
                session.setAttribute("msg", "User not registered!");
            }
        }

        return "redirect:/signin";
    }



}
