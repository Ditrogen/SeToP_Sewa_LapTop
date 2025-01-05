package com.pbokelar.amin.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class LaptopDto {

    @NotEmpty(message="Model Name is required") private String model_name;
    @NotEmpty(message="Processor is required") private String processor;
    @NotEmpty(message="Ram Size is required") private String ram_size;
    @NotEmpty(message="Storage Size is required") private String storage_size;
    @NotEmpty(message="Screen Size is required") private String screen_size;
    @NotEmpty(message="Brand Name is required") private String brand_name;
    @NotEmpty(message="Type is required") private String type;
    @Min(1000) private int price_per_day;
    @Min(1) private int max_lend_duration;
    
    
    public String getModel_name() {
        return model_name;
    }
    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }
    public String getProcessor() {
        return processor;
    }
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    public String getRam_size() {
        return ram_size;
    }
    public void setRam_size(String ram_size) {
        this.ram_size = ram_size;
    }
    public String getStorage_size() {
        return storage_size;
    }
    public void setStorage_size(String storage_size) {
        this.storage_size = storage_size;
    }
    public String getScreen_size() {
        return screen_size;
    }
    public void setScreen_size(String screen_size) {
        this.screen_size = screen_size;
    }
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
