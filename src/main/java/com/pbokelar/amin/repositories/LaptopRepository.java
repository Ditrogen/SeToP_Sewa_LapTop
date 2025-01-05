package com.pbokelar.amin.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pbokelar.amin.models.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Integer>{
    public List<Laptop> findByType(String type, Sort sort);
}
