package com.basic.Basic_Project_Spring.ship.presentation.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ShipRequest(
        @NotBlank String name,
        @NotBlank String imagePath,
        int seats,
        @NotBlank String type,
        int speed,
        double weight,
        double length,
        double width,
        double height,
        LocalDate launchDate
) {
}
