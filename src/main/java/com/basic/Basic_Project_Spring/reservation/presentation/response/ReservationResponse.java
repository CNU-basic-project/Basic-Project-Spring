package com.basic.Basic_Project_Spring.reservation.presentation.response;

import com.basic.Basic_Project_Spring.member.presentation.response.MemberResponse;
import com.basic.Basic_Project_Spring.reservation.domain.Reservation;
import com.basic.Basic_Project_Spring.ship.presentation.response.ShipResponse;

public record ReservationResponse(
        Long id,
        MemberResponse memberResponse,
        ShipResponse shipResponse
) {

    public static ReservationResponse of(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                new MemberResponse(
                        reservation.getMember().getName()
                ),
                new ShipResponse(
                        reservation.getShip().getName(),
                        reservation.getShip().getImagePath(),
                        reservation.getShip().getSeats(),
                        reservation.getShip().getType(),
                        reservation.getShip().getSpeed(),
                        reservation.getShip().getWeight(),
                        reservation.getShip().getLength(),
                        reservation.getShip().getWidth(),
                        reservation.getShip().getHeight()
                )
        );
    }
}
