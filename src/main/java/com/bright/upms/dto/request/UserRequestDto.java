package com.bright.upms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "blank/null/empty not allowed")
        String username,
//        @Size(min = 4, max = 8)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{4,8}$")
        String password,
        @NotNull
        ProfileRequestDto profileRequestDto
) {
}
