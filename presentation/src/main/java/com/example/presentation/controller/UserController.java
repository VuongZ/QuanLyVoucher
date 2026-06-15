package com.example.presentation.controller;

import com.example.application.common.ApiResponse;
import com.example.application.common.PageResult;
import com.example.application.dto.UserDto;
import com.example.application.dto.UserRequest;
import com.example.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<UserDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách user thành công", userService.getAll(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Lấy user thành công", userService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> create(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(201).body(ApiResponse.success("Tạo user thành công", userService.create(request)));
    }
}