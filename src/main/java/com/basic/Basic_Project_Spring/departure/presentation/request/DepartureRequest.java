package com.basic.Basic_Project_Spring.departure.presentation.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

public record DepartureRequest(
        LocalDate date,
        @NotBlank String departures,
        LocalTime departureTime,
        @NotBlank String arrivals,
        LocalTime arrivalTime,
        int price,
        Long shipId
) {
}
