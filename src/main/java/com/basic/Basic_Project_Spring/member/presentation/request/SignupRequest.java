package com.basic.Basic_Project_Spring.member.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name
) {
}
