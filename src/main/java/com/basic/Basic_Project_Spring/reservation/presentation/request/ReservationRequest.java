package com.basic.Basic_Project_Spring.reservation.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record ReservationRequest(
        @NotBlank Long departureId
) {
}
