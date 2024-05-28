package com.basic.Basic_Project_Spring.reservation.domain;

import com.basic.Basic_Project_Spring.departure.domain.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Transactional
    void deleteAllByDeparture(Departure departure);

    List<Reservation> findAllByMemberId(Long memberId);

    List<Reservation> findAllByDepartureId(Long departureId);
}
