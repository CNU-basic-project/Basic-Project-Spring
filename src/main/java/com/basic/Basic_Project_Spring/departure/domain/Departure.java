package com.basic.Basic_Project_Spring.departure.domain;

import com.basic.Basic_Project_Spring.common.exception.RejectReservationException;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class Departure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String departures;
    private LocalTime departureTime;
    private String arrivals;
    private LocalTime arrivalTime;
    private int price;
    private int seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Departure(
            LocalDate date,
            String departures,
            LocalTime departureTime,
            String arrivals,
            LocalTime arrivalTime,
            int price,
            int seat,
            Ship ship
    ) {
        this.date = date;
        this.departures = departures;
        this.departureTime = departureTime;
        this.arrivals = arrivals;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.seat = seat;
        this.ship = ship;
    }

    public void reservation() {
        if (ship.getSeats() > seat) {
            seat++;
            return;
        }
        throw new RejectReservationException("더 예약할 수 없습니다.");
    }

    public void cancel() {
        seat--;
        if (seat < 0) {
            seat = 0;
            throw new RejectReservationException("취소할 예약이 없습니다.");
        }
    }
}
