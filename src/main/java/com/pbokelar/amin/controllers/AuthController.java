package com.pbokelar.amin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbokelar.amin.models.User;
import com.pbokelar.amin.repositories.UserRepository;






@Controller
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String validateLogin(@RequestParam String username, @RequestParam String password) {

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole().equals("USER")) {
                user.setLoggedin(1);

                userRepository.save(user);

                return "redirect:/enduser";
            } else if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin";
            }
        }
        
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        User user = userRepository.findByLoggedin(1);
        
        if (user != null) {
            user.setLoggedin(0);
            userRepository.save(user);
        }
        
        return "redirect:/";
    }
    
    
    
    
    
}
