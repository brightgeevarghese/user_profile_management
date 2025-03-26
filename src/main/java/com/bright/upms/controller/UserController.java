package com.bright.upms.controller;

import com.bright.upms.dto.request.UserRequestDto;
import com.bright.upms.dto.response.UserResponseDto;
import com.bright.upms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtoList = userService.findAllUsers();
        if (!userResponseDtoList.isEmpty()) {
            return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        Optional<UserResponseDto> userResponseDto = userService.createUser(userRequestDto);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String username) {
        Optional<UserResponseDto> userResponseDto = userService.findUserByUsername(username);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.ok(userResponseDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String username, @RequestBody @Valid UserRequestDto userRequestDto) {
        Optional<UserResponseDto> userResponseDto = userService.updateUser(username, userRequestDto);
        if (userResponseDto.isPresent()) {
            return ResponseEntity.ok(userResponseDto.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
