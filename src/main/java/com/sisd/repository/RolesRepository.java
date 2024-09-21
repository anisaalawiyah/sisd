package com.sisd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sisd.entity.Roles;
import org.springframework.data.jpa.repository.Query;

public interface RolesRepository extends JpaRepository<Roles, String> {
    @Query
    Roles findByRoleName(String roleName);
}
