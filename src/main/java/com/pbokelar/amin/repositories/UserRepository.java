package com.pbokelar.amin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbokelar.amin.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    public User findByUsername(String username);
    public User findByLoggedin(int loggedin);
}
