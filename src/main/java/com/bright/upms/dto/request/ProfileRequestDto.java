package com.bright.upms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ProfileRequestDto(
        @NotBlank(message = "empty/null/white space is not allowed")
        String biodata,
        @NotBlank(message = "empty/null/white space is not allowed")
        String location,
        @NotNull
        LocalDate birthday,
        @NotBlank(message = "empty/null/white space is not allowed")
        @Pattern(regexp = "^(male|female)$", message = "Gender must be male or female")
        String gender
) {
}
