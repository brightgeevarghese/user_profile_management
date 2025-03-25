package com.bright.upms.service.impl;

import com.bright.upms.dto.request.ProfileRequestDto;
import com.bright.upms.dto.request.UserRequestDto;
import com.bright.upms.dto.response.ProfileResponseDto;
import com.bright.upms.dto.response.UserResponseDto;
import com.bright.upms.mapper.ProfileMapper;
import com.bright.upms.mapper.UserMapper;
import com.bright.upms.model.Profile;
import com.bright.upms.model.User;
import com.bright.upms.repository.UserRepository;
import com.bright.upms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;

    @Override
    public Optional<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        if (userRepository.findByUsername(userRequestDto.username()).isPresent()) {
            return Optional.empty();
        }
        User user = userMapper.userRequestDtoToUser(userRequestDto);
        System.out.println("user is "+user);
        User savedUser = userRepository.save(user);
        UserResponseDto userResponseDto = userMapper.userToUserResponseDto(savedUser);
        System.out.println("saved user is "+userResponseDto);
        return Optional.of(userResponseDto);
    }

    @Override
    public Optional<UserResponseDto> updateUser(String username, UserRequestDto userRequestDto) {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setPassword(userRequestDto.password());
//            if (userRequestDto.profileRequestDto() != null) {
//                Profile profile = profileMapper.profileRequestDtoToProfile(userRequestDto.profileRequestDto());
//                profile.setProfileId(user.getProfile().getProfileId());
//                user.setProfile(profile);
//            }
//            User updatedUser = userRepository.save(user);
//            UserResponseDto userResponseDto = userMapper.userToUserResponseDto(updatedUser);
//            return Optional.of(userResponseDto);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            userMapper.updateUserFromUserRequestDto(userRequestDto, existingUser);
            User savedUser = userRepository.save(existingUser);
            UserResponseDto userResponseDto = userMapper.userToUserResponseDto(savedUser);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    @Override
    public Optional<UserResponseDto> findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponseDto userResponseDto = userMapper.userToUserResponseDto(user);
            return Optional.of(userResponseDto);
        }
        return Optional.empty();
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = userMapper.userToUserResponseDtoList(userList);
        if (!userResponseDtoList.isEmpty()) {
            return userResponseDtoList;
        }
        return List.of();
    }
}
