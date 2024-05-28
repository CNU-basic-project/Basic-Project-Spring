package com.basic.Basic_Project_Spring.departure.presentation.response;

import com.basic.Basic_Project_Spring.departure.domain.Departure;
import com.basic.Basic_Project_Spring.ship.presentation.response.ShipResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public record DepartureResponse(
        Long id,
        LocalDate date,
        String departures,
        LocalTime departureTime,
        String arrivals,
        LocalTime arrivalTime,
        int price,
        int seat,
        ShipResponse shipResponse
) {

    public static DepartureResponse of(Departure departure) {
        return new DepartureResponse(
                departure.getId(),
                departure.getDate(),
                departure.getDepartures(),
                departure.getDepartureTime(),
                departure.getArrivals(),
                departure.getArrivalTime(),
                departure.getPrice(),
                departure.getSeat(),
                ShipResponse.of(departure.getShip())
        );
    }
}
