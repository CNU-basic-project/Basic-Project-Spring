package com.basic.Basic_Project_Spring.post.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
        @NotBlank String title,
        @NotBlank String content
) {
}
