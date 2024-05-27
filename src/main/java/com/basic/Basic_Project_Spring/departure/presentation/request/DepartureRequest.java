package com.basic.Basic_Project_Spring.departure.presentation.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

public record DepartureRequest(
        @NotBlank LocalDate date,
        @NotBlank String departures,
        @NotBlank LocalTime departureTime,
        @NotBlank String arrivals,
        @NotBlank LocalTime arrivalTime,
        @NotBlank int price,
        @NotBlank Long shipId
) {
}
