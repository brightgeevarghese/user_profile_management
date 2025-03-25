package com.bright.upms.service;

import com.bright.upms.dto.request.UserRequestDto;
import com.bright.upms.dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> createUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto);
    void deleteUser(String username);
    Optional<UserResponseDto> findUserByUsername(String username);
    List<UserResponseDto> findAllUsers();
}
