package com.basic.Basic_Project_Spring.reservation.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    void deleteAllByDepartureId(Long departureId);
    List<Reservation> findAllByMemberId(Long memberId);
    List<Reservation> findAllByDepartureId(Long departureId);
}
