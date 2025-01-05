package com.pbokelar.amin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbokelar.amin.models.RequestItem;

public interface RequestItemRepository extends JpaRepository<RequestItem, Integer>{
    public List<RequestItem> findByUserId(int user_id);
    public List<RequestItem> findByLaptopId(int laptop_id);
}
