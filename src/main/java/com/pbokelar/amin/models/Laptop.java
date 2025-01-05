package com.pbokelar.amin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "laptops")
public class Laptop extends BaseLaptop{

    @Column(nullable=false) private String brand_name;
    @Column(nullable=false) private String type;
    @Column(nullable=false) private int price_per_day;
    @Column(nullable=false) private int max_lend_duration;
    
    
    public String getBrand_name() {
        return brand_name;
    }
    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPrice_per_day() {
        return price_per_day;
    }
    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
    }
    public int getMax_lend_duration() {
        return max_lend_duration;
    }
    public void setMax_lend_duration(int max_lend_duration) {
        this.max_lend_duration = max_lend_duration;
    }


    
}
