package com.pbokelar.amin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbokelar.amin.models.Laptop;
import com.pbokelar.amin.models.LaptopDto;
import com.pbokelar.amin.models.RequestItem;
import com.pbokelar.amin.repositories.LaptopRepository;
import com.pbokelar.amin.repositories.RequestItemRepository;

import jakarta.validation.Valid;





@Controller
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private RequestItemRepository requestItemRepository;

    @GetMapping("admin/createLaptop")
    public String createLaptopPage(Model model) {
        LaptopDto laptopDto = new LaptopDto();
        model.addAttribute("laptopDto",laptopDto);
        return "admin/createLaptop";
    }

    @GetMapping("admin/detailLaptop")
    public String detailLaptopAdmin(Model model, @RequestParam int id) {
        
        Laptop laptop = laptopRepository.findById(id).orElse(null);
        if (laptop == null) {
            return "redirect:/admin";
        }

        model.addAttribute("laptop",laptop);


        return "admin/detailLaptop";
    }

    @GetMapping("admin/editLaptop")
    public String editLaptopPage(Model model, @RequestParam int id) {
        
        Laptop laptop = laptopRepository.findById(id).orElse(null);
        if (laptop == null) {
            return "redirect:/admin";
        }

        LaptopDto laptopDto = new LaptopDto();
        laptopDto.setModel_name(laptop.getModel_name());
        laptopDto.setProcessor(laptop.getProcessor());
        laptopDto.setRam_size(laptop.getRam_size());
        laptopDto.setStorage_size(laptop.getStorage_size());
        laptopDto.setScreen_size(laptop.getScreen_size());
        laptopDto.setBrand_name(laptop.getBrand_name());
        laptopDto.setType(laptop.getType());
        laptopDto.setPrice_per_day(laptop.getPrice_per_day());
        laptopDto.setMax_lend_duration(laptop.getMax_lend_duration());

        model.addAttribute("laptop", laptop);
        model.addAttribute("laptopDto",laptopDto);

        
        return "admin/editLaptop";
    }

    @GetMapping("admin/deleteLaptop")
    public String deleteLaptop(@RequestParam int id) {
        
        List<RequestItem> requestItem = requestItemRepository.findByLaptopId(id);
        if (!requestItem.isEmpty()) {
            requestItemRepository.deleteAll(requestItem);
        }

        Laptop laptop = laptopRepository.findById(id).orElse(null);
        if (laptop != null) {
            laptopRepository.delete(laptop);
        }
        
        
        return "redirect:/admin";
    }
    
    @GetMapping("enduser/detailLaptop")
    public String detailLaptopEnduser(Model model, @RequestParam int id) {
        
        Laptop laptop = laptopRepository.findById(id).orElse(null);
        if (laptop == null) {
            return "redirect:/enduser";
        }

        model.addAttribute("laptop",laptop);


        return "enduser/detailLaptop";
    }
    
    
    @PostMapping("admin/createLaptop")
    public String crateLaptop(@Valid LaptopDto laptopDto, BindingResult result) {
        
        if (result.hasErrors()) {
            return "admin/createLaptop";
        }
        
        Laptop laptop = new Laptop();
        laptop.setModel_name(laptopDto.getModel_name());
        laptop.setProcessor(laptopDto.getProcessor());
        laptop.setRam_size(laptopDto.getRam_size());
        laptop.setStorage_size(laptopDto.getStorage_size());
        laptop.setScreen_size(laptopDto.getScreen_size());
        laptop.setBrand_name(laptopDto.getBrand_name());
        laptop.setType(laptopDto.getType());
        laptop.setPrice_per_day(laptopDto.getPrice_per_day());
        laptop.setMax_lend_duration(laptopDto.getMax_lend_duration());

        laptopRepository.save(laptop);


        return "redirect:/admin";
    }

    @PostMapping("admin/editLaptop")
    public String editLaptop(Model model, @RequestParam int id, @Valid @ModelAttribute LaptopDto laptopDto, BindingResult result) {
        
        Laptop laptop = laptopRepository.findById(id).orElse(null);
        if (laptop == null) {
            return "redirect:/admin";
        }

        model.addAttribute("laptop", laptop);

        if (result.hasErrors()) {
            return "admin/editLaptop";
        }

        laptop.setModel_name(laptopDto.getModel_name());
        laptop.setProcessor(laptopDto.getProcessor());
        laptop.setRam_size(laptopDto.getRam_size());
        laptop.setStorage_size(laptopDto.getStorage_size());
        laptop.setScreen_size(laptopDto.getScreen_size());
        laptop.setBrand_name(laptopDto.getBrand_name());
        laptop.setType(laptopDto.getType());
        laptop.setPrice_per_day(laptopDto.getPrice_per_day());
        laptop.setMax_lend_duration(laptopDto.getMax_lend_duration());

        laptopRepository.save(laptop);
        
        return "redirect:/admin";
    }
    
    
}
