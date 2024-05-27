package com.basic.Basic_Project_Spring.ship.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record ShipRequest(
        @NotBlank String name,
        @NotBlank String imagePath,
        @NotBlank int seats,
        @NotBlank String type,
        @NotBlank int speed,
        @NotBlank double weight,
        @NotBlank double length,
        @NotBlank double width,
        @NotBlank double height
) {
}
