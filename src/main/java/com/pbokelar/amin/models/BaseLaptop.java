package com.pbokelar.amin.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseLaptop {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false) private String model_name;
    @Column(nullable=false) private String processor;
    @Column(nullable=false) private String ram_size;
    @Column(nullable=false) private String storage_size;
    @Column(nullable=false) private String screen_size;
    

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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

    

}
