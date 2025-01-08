package com.pbokelar.amin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbokelar.amin.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    public Role findByRole(String role);
}
