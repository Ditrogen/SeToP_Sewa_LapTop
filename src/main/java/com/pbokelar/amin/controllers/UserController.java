package com.pbokelar.amin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbokelar.amin.models.Role;
import com.pbokelar.amin.models.User;
import com.pbokelar.amin.models.UserDto;
import com.pbokelar.amin.repositories.RoleRepository;
import com.pbokelar.amin.repositories.UserRepository;

import jakarta.validation.Valid;





@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        User user = new User();
        Role role = roleRepository.findByRole("USER");

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(role);
        user.setName(userDto.getName());
        user.setPhone_number(userDto.getPhone_number());
        user.setAddress(userDto.getAddress());
        user.setLoggedin(0);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            result.addError(
                new FieldError("userDto", "username", userDto.getUsername()
                , false, null, null, "Username already exists!"));
            
                return "register";
        }

        return "redirect:/login";
    }
    
}
