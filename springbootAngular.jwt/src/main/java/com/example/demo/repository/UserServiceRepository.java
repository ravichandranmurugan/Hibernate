package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modal.UserService;

@Repository
public interface UserServiceRepository extends JpaRepository<UserService, Long> {
 UserService   findByUserName(String firstName);
 UserService  findByEmail(String email);
}
