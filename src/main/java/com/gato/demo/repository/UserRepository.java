package com.gato.demo.repository;

import com.gato.demo.model.Server;
import com.gato.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
}
