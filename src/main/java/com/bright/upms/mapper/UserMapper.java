package com.bright.upms.mapper;

import com.bright.upms.dto.request.UserRequestDto;
import com.bright.upms.dto.response.UserResponseDto;
import com.bright.upms.model.Profile;
import com.bright.upms.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //Convert a UserRequestDto to User
    @Mapping(source = "profileRequestDto", target = "profile")
    User userRequestDtoToUser(UserRequestDto userRequestDto);

    //Convert a User to UserResponseDto
    @Mapping(source = "profile", target = "profileResponseDto")
    UserResponseDto userToUserResponseDto(User user);

    @Mapping(source = "profile", target = "profileResponseDto")
    List<UserResponseDto> userToUserResponseDtoList(List<User> userList);

    void updateUserFromUserRequestDto(UserRequestDto userRequestDto, @MappingTarget User existingUser);

    @AfterMapping
    default void afterUserUpdate(UserRequestDto userRequestDto, @MappingTarget User existingUser) {
        if (userRequestDto.profileRequestDto() != null) {
            if (existingUser.getProfile() == null) {
                existingUser.setProfile(new Profile());
            } else {
                existingUser.getProfile().setBiodata(userRequestDto.profileRequestDto().biodata());
                existingUser.getProfile().setBirthday(userRequestDto.profileRequestDto().birthday());
                existingUser.getProfile().setGender(userRequestDto.profileRequestDto().gender());
                existingUser.getProfile().setLocation(userRequestDto.profileRequestDto().location());
                existingUser.getProfile().setGender(userRequestDto.profileRequestDto().gender());
            }
        }
    }

    @BeforeMapping
    default void beforeUserUpdate(UserRequestDto userRequestDto, @MappingTarget User existingUser) {
        System.out.println("Before updating User.  Request: " + userRequestDto + ", Existing User: " );
        if (userRequestDto.username() == null || userRequestDto.username().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }
}
