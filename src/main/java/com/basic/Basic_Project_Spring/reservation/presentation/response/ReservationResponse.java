package com.basic.Basic_Project_Spring.reservation.presentation.response;

import com.basic.Basic_Project_Spring.departure.presentation.response.DepartureResponse;
import com.basic.Basic_Project_Spring.member.presentation.response.MemberResponse;
import com.basic.Basic_Project_Spring.reservation.domain.Reservation;

public record ReservationResponse(
        Long id,
        MemberResponse memberResponse,
        DepartureResponse departureResponse
) {

    public static ReservationResponse of(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                new MemberResponse(
                        reservation.getMember().getName()
                ),
                DepartureResponse.of(reservation.getDeparture())
        );
    }
}
