package com.pbokelar.amin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pbokelar.amin.repositories.LaptopRepository;
import com.pbokelar.amin.repositories.UserRepository;



@Controller
public class ViewController {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping({"","/"})
    public String landingPage() {
        return "landingPage"; // ini harus diganti klo mau implement authentication
    }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/enduser")
    public String userDashboard(Model model) {

        var user = userRepository.findByLoggedin(1);

        //Sort sort = Sort.by(Sort.Direction.DESC, "type");
        //var laptops = laptopRepository.findByType("Gaming", sort);
        var laptops = laptopRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

        model.addAttribute("laptops", laptops);
        model.addAttribute("user", user);
        
        return "enduser/userDashboard";
    }
    
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        var laptops = laptopRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("laptops", laptops);

        return "admin/adminDashboard";
    }


    
    

    
}
