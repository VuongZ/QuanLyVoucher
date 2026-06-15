package com.example.domain.repository;

import com.example.domain.entity.user;

import java.util.List;
import java.util.Optional;

public interface userRepo {
    List<user> GetAll();
    Optional<user> findById(Integer id);
    user Save(user u);
}
