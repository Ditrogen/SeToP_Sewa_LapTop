package com.pbokelar.amin.models;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class RequestItemDto {

    private Laptop laptop;
    private User user;
    @NotNull(message = "Please pick a starting date!") private LocalDate start_date;
    @NotNull(message = "Please pick a return date!") private LocalDate expected_return_date;
    private LocalDate actual_return_date;
    private int fines;
    private String status;
    private int total;
    private int damaged;
    
    
    public Laptop getLaptop() {
        return laptop;
    }
    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDate getStart_date() {
        return start_date;
    }
    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
    public LocalDate getExpected_return_date() {
        return expected_return_date;
    }
    public void setExpected_return_date(LocalDate expected_return_date) {
        this.expected_return_date = expected_return_date;
    }
    public LocalDate getActual_return_date() {
        return actual_return_date;
    }
    public void setActual_return_date(LocalDate actual_return_date) {
        this.actual_return_date = actual_return_date;
    }
    public int getFines() {
        return fines;
    }
    public void setFines(int fines) {
        this.fines = fines;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getDamaged() {
        return damaged;
    }
    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }
    
    

    
}
