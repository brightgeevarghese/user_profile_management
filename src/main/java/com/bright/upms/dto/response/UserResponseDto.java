package com.bright.upms.dto.response;

public record UserResponseDto(
        String username,
        ProfileResponseDto profileResponseDto
) {
}
