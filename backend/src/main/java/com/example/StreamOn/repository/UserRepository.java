package com.example.StreamOn.repository;

import com.example.StreamOn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
