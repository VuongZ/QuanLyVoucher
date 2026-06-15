package com.example.infrastructure.adapter;

import com.example.domain.entity.user;
import com.example.domain.repository.userRepo;
import com.example.infrastructure.Repositories.UserJpaRepository;
import com.example.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepoJpaIml implements userRepo {
    private final UserJpaRepository repo;
    public UserRepoJpaIml(UserJpaRepository repo)
    {
        this.repo=repo;
    }
    @Override
    public List<user> GetAll() {
        return repo.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public Optional<user> findById(Integer id) {
        return repo.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public user Save(user u) {
        return UserMapper.toDomain(repo.save(UserMapper.toJpa(u)));
    }
}
