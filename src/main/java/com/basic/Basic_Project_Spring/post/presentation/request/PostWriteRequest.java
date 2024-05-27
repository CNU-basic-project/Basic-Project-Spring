package com.basic.Basic_Project_Spring.post.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record PostWriteRequest(
        @NotBlank String title,
        @NotBlank String content
) {
}
