// service/UserServiceImpl.java
package com.example.application.service;

import com.example.application.common.PageResult;
import com.example.application.dto.UserDto;
import com.example.application.dto.UserRequest;
import com.example.domain.entity.user;
import com.example.domain.repository.userRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final userRepo userRepo;

    public UserServiceImpl(userRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public PageResult<UserDto> getAll(int page, int size) {
        List<UserDto> all = userRepo.GetAll()
                .stream()
                .map(this::toDto)
                .toList();

        long total = all.size();
        List<UserDto> paged = all.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        return new PageResult<>(paged, page, size, total);
    }

    @Override
    public UserDto getById(Integer id) {
        user u = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User không tồn tại: " + id));
        return toDto(u);
    }

    @Override
    public UserDto create(UserRequest request) {
        user u = new user();
        u.setFullname(request.getFullname());
        u.setEmail(request.getEmail());
        u.setPhone(request.getPhone());
        u.setCreatedAt(LocalDateTime.now());
        return toDto(userRepo.Save(u));
    }

    private UserDto toDto(user u) {
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setFullname(u.getFullname());
        dto.setEmail(u.getEmail());
        dto.setPhone(u.getPhone());
        dto.setCreatedAt(u.getCreatedAt());
        return dto;
    }
}