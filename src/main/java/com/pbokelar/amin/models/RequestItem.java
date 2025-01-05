package com.pbokelar.amin.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "request_items")
public class RequestItem {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate start_date;
    private LocalDate expected_return_date;
    private LocalDate acttual_return_date;
    private int fines;
    private String status; // REQ, ACT, RET ,FIN
    private int total;
    private int damaged;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDate getActtual_return_date() {
        return acttual_return_date;
    }

    public void setActtual_return_date(LocalDate acttual_return_date) {
        this.acttual_return_date = acttual_return_date;
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
