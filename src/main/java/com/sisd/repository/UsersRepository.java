package com.sisd.repository;

import com.sisd.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,String> {

    Optional<Users> findByUserName(String username);
}
