// service/UserService.java
package com.example.application.service;

import com.example.application.common.PageResult;
import com.example.application.dto.UserDto;
import com.example.application.dto.UserRequest;

public interface UserService {
    PageResult<UserDto> getAll(int page, int size);
    UserDto getById(Integer id);
    UserDto create(UserRequest request);
}