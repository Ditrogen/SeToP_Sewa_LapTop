package com.pbokelar.amin.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbokelar.amin.models.Laptop;
import com.pbokelar.amin.models.RequestItem;
import com.pbokelar.amin.models.RequestItemDto;
import com.pbokelar.amin.models.User;
import com.pbokelar.amin.repositories.LaptopRepository;
import com.pbokelar.amin.repositories.RequestItemRepository;
import com.pbokelar.amin.repositories.UserRepository;

import jakarta.validation.Valid;









@Controller

public class RequestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private RequestItemRepository requestItemRepository;

    @GetMapping("admin/viewRequest")
    public String requestAdminPage(Model model) {
        var requestItems = requestItemRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("requestItems", requestItems);
        
        return "admin/viewRequest";
    }

    @GetMapping("admin/cancelRequest")
    public String cancelRequestAdmin(@RequestParam int id) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem != null) {
            requestItemRepository.delete(requestItem);
        }
        
        
        return "redirect:/admin/viewRequest";
    }

    @GetMapping("admin/approveRequest")
    public String approveRequest(@RequestParam int id) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem != null) {
            requestItem.setStatus("ACT");
            requestItemRepository.save(requestItem);
        }
        
        
        return "redirect:/admin/viewRequest";
    }

    @GetMapping("admin/editRequest")
    public String editRequestAdminPage(Model model, @RequestParam int id) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem == null) {
            return "redirect:/admin/viewRequest";
        }

        RequestItemDto requestItemDto = new RequestItemDto();
        requestItemDto.setUser(requestItem.getUser());
        requestItemDto.setLaptop(requestItem.getLaptop());
        requestItemDto.setStart_date(requestItem.getStart_date());
        requestItemDto.setExpected_return_date(requestItem.getExpected_return_date());
        requestItemDto.setActual_return_date(requestItem.getActtual_return_date());
        requestItemDto.setFines(requestItem.getFines());
        requestItemDto.setStatus(requestItem.getStatus());
        requestItemDto.setTotal(requestItem.getTotal());

        model.addAttribute("requestItem", requestItem);
        model.addAttribute("requestItemDto", requestItemDto);


        return "admin/editRequest";
    }
    
    
    
    
    @GetMapping("enduser/viewRequest")
    public String requestUserPage(Model model) {
        
        User user = userRepository.findByLoggedin(1);
        
        var requestItems = requestItemRepository.findByUserId(user.getId());
        model.addAttribute("requestItems", requestItems);
        
        return "enduser/viewRequest";
    }
    
    @GetMapping("enduser/addRequest")
    public String addRequestEnduser(Model model, @RequestParam int id) {
        
        RequestItemDto requestItemDto = new RequestItemDto();
        var laptop = laptopRepository.findById(id);
        var user = userRepository.findByLoggedin(1);

        LocalDate today = LocalDate.now();
        
        model.addAttribute("min", today);
        model.addAttribute("requestItemDto", requestItemDto);
        model.addAttribute("laptop", laptop);
        model.addAttribute("user", user);


        return "enduser/addRequest";
    }

    @GetMapping("enduser/cancelRequest")
    public String cancelRequestEnduser(@RequestParam int id) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem != null) {
            requestItemRepository.delete(requestItem);
        }
        
        
        return "redirect:/enduser/viewRequest";
    }

    @GetMapping("enduser/returnRequest")
    public String returnRequestEnduser(Model model, @RequestParam int id) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem == null) {
            return "enduser/viewRequest";
        }

        RequestItemDto requestItemDto = new RequestItemDto();

        requestItemDto.setUser(requestItem.getUser());
        requestItemDto.setLaptop(requestItem.getLaptop());
        requestItemDto.setStart_date(requestItem.getStart_date());
        requestItemDto.setExpected_return_date(requestItem.getExpected_return_date());
        requestItemDto.setActual_return_date(requestItem.getActtual_return_date());
        requestItemDto.setFines(requestItem.getFines());
        requestItemDto.setStatus(requestItem.getStatus());
        requestItemDto.setTotal(requestItem.getTotal());

        model.addAttribute("requestItemDto", requestItemDto);
        model.addAttribute("min", requestItem.getStart_date().toString());
        
        
        return "enduser/returnRequest";
    }

    @GetMapping("admin/checkRequest")
    public String checkRequestAdmin(Model model, @RequestParam int id) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem == null) {
            return "redirect:/admin/viewRequest";
        }

        RequestItemDto requestItemDto = new RequestItemDto();
        requestItemDto.setUser(requestItem.getUser());
        requestItemDto.setLaptop(requestItem.getLaptop());
        requestItemDto.setStart_date(requestItem.getStart_date());
        requestItemDto.setExpected_return_date(requestItem.getExpected_return_date());
        requestItemDto.setActual_return_date(requestItem.getActtual_return_date());
        requestItemDto.setFines(requestItem.getFines());
        requestItemDto.setStatus(requestItem.getStatus());
        requestItemDto.setTotal(requestItem.getTotal());
        requestItemDto.setDamaged(requestItem.getDamaged());

        model.addAttribute("requestItemDto", requestItemDto);
        
        return "admin/checkRequest";
    }

    @GetMapping("admin/createRequest")
    public String createRequestAdmin(Model model) {
        
        RequestItemDto requestItemDto = new RequestItemDto();

        LocalDate today = LocalDate.now();

        model.addAttribute("min", today);
        model.addAttribute("requestItemDto", requestItemDto);
        
        return "admin/createRequest";
    }
    

    @PostMapping("admin/createRequest")
    public String postMethodName(Model model,@Valid @ModelAttribute RequestItemDto requestItemDto, @RequestParam String username, @RequestParam int laptop_id, BindingResult result) {
        
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "admin/createRequest";
        }

        Laptop laptop = laptopRepository.findById(laptop_id).orElse(null);
        if (laptop == null) {
            return "admin/createRequest";
        }

        if (result.hasErrors()) {
            return "admin/createRequest";
        }

        LocalDate start_date = requestItemDto.getStart_date();
        LocalDate end_date = requestItemDto.getExpected_return_date();
        int dayDurExpected = (int) ChronoUnit.DAYS.between(start_date, end_date);
        

        RequestItem requestItem = new RequestItem();
        requestItem.setLaptop(laptop);
        requestItem.setUser(user);
        requestItem.setStart_date(requestItemDto.getStart_date());
        requestItem.setExpected_return_date(requestItemDto.getExpected_return_date());
        requestItem.setStatus("REQ");
        requestItem.setTotal(dayDurExpected * laptop.getPrice_per_day());

        requestItemRepository.save(requestItem);

        
        return "redirect:/admin/viewRequest";
    }
    



    @PostMapping("admin/checkRequest")
    public String checkRequestAdmin(@Valid @ModelAttribute RequestItemDto requestItemDto, @RequestParam int id , Model model, BindingResult result) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem == null) {
            return "admin/viewRequest";
        }

        model.addAttribute("requestItemDto", requestItemDto);
        if (result.hasErrors()) {
            return "admin/viewRequest";
        }

        if (requestItemDto.getDamaged() > 0) {
            int fines = requestItemDto.getDamaged() * 10000;
            requestItem.setFines(requestItem.getFines() + fines);
            requestItem.setTotal(requestItem.getTotal() + fines);
        }

        requestItem.setDamaged(requestItemDto.getDamaged());
        requestItem.setStatus("FIN");

        requestItemRepository.save(requestItem);
        
        return "redirect:/admin/viewRequest";
    }
    
    
    

    @PostMapping("admin/editRequest")
    public String editRequestAdmin(Model model, @RequestParam int id ,@Valid @ModelAttribute RequestItemDto requestItemDto, BindingResult result) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem == null) {
            return "redirect:/admin/viewRequest";
        }

        model.addAttribute("requestItemDto", requestItemDto);
        if (result.hasErrors()) {
            return "admin/editRequest";
        }

        requestItem.setStart_date(requestItemDto.getStart_date());
        requestItem.setExpected_return_date(requestItemDto.getExpected_return_date());
        requestItem.setActtual_return_date(requestItemDto.getActual_return_date());
        requestItem.setFines(requestItemDto.getFines());
        requestItem.setStatus(requestItemDto.getStatus());
        requestItem.setTotal(requestItemDto.getTotal());

        requestItemRepository.save(requestItem);
        
        return "redirect:/admin/viewRequest";
    }
    
    

    @PostMapping("enduser/addRequest")
    public String postMethodName(@Valid @ModelAttribute RequestItemDto requestItemDto, @ModelAttribute Laptop laptop,BindingResult result) {
        
        LocalDate start_date = requestItemDto.getStart_date();
        LocalDate end_date = requestItemDto.getExpected_return_date();
        User user = userRepository.findByLoggedin(1);

        if (user == null) {
            return "/";
        }


        int dayDifference = (int) ChronoUnit.DAYS.between(start_date, end_date);

        if (dayDifference > laptop.getMax_lend_duration()) {
            result.addError(
                new FieldError("requestItemDto", "expected_return_date", requestItemDto.getExpected_return_date(), false, null, null, "Date cannot exceed max lending duration")
                );
        }

        if (dayDifference < 0) {
            result.addError(
                new FieldError("requestItemDto", "expected_return_date", requestItemDto.getExpected_return_date(), false, null, null, "Please input valid date!")
                );
        }

        if (result.hasErrors()) {
            return "enduser/addRequest";
        }


        
        RequestItem requestItem = new RequestItem();
        requestItem.setLaptop(laptop);
        requestItem.setUser(user);
        requestItem.setStart_date(requestItemDto.getStart_date());
        requestItem.setExpected_return_date(requestItemDto.getExpected_return_date());
        requestItem.setFines(0);
        requestItem.setStatus("REQ");
        requestItem.setTotal(dayDifference*laptop.getPrice_per_day());

        requestItemRepository.save(requestItem);
        
        return "redirect:/enduser/viewRequest";
    }
    
    @PostMapping("enduser/returnRequest")
    public String returnRequestEnduser(Model model, @RequestParam int id, @Valid @ModelAttribute RequestItemDto requestItemDto, BindingResult result) {
        
        RequestItem requestItem = requestItemRepository.findById(id).orElse(null);
        if (requestItem == null) {
            return "enduser/viewRequest";
        }

        model.addAttribute("RequestItemDto", requestItemDto);
        if (result.hasErrors()) {
            return "enduser/returnRequest";
        }

        LocalDate expected_return_date = requestItem.getExpected_return_date();
        LocalDate actual_return_date = requestItemDto.getActual_return_date();
        int dayDifference = (int) ChronoUnit.DAYS.between(expected_return_date, actual_return_date);
        
        
        if (dayDifference > 0) {
            int fine = dayDifference * 10000;
            requestItem.setFines(fine);
            requestItem.setTotal(requestItem.getTotal()+fine);
        }



        requestItem.setActtual_return_date(requestItemDto.getActual_return_date());
        requestItem.setStatus("RET");

        requestItemRepository.save(requestItem);

        return "redirect:/enduser/viewRequest";
    }
    
    
}
