package com.basic.Basic_Project_Spring.departure.application;

import com.basic.Basic_Project_Spring.common.exception.NotFoundException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.departure.domain.Departure;
import com.basic.Basic_Project_Spring.departure.domain.DepartureRepository;
import com.basic.Basic_Project_Spring.departure.presentation.request.DepartureRequest;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.reservation.domain.ReservationRepository;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.domain.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartureService {

    private final DepartureRepository departureRepository;
    private final ShipRepository shipRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    public List<Departure> getByShip(Ship ship) {
        return departureRepository.findAllByShip(ship);
    }

    public List<Departure> getBySearch(String keyword) {
        return departureRepository.findAllByDeparturesContainingOrArrivalsContaining(keyword, keyword);
    }

    public List<Departure> getByDate(LocalDate date) {
        return departureRepository.findAllByDate(date);
    }

    public List<Departure> getByQuery(LocalDate date, String keyword) {
        return departureRepository.findAllByDateAndQuery(date, keyword);
    }

    public List<Departure> getDepartures() {
        return departureRepository.findAll();
    }

    public Long add(
            Long memberId,
            DepartureRequest request
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = shipRepository.findById(request.shipId())
                .orElseThrow(() -> new NotFoundException("배 정보가 존재하지 않습니다."));
        ship.validateAuthority(member);
        Departure departure = new Departure(
                request.date(),
                request.departures(),
                request.departureTime(),
                request.arrivals(),
                request.arrivalTime(),
                request.price(),
                0,
                ship
        );
        return departureRepository.save(departure).getId();
    }

    public void delete(
            Long memberId,
            Long departureId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Departure departure = departureRepository.findById(departureId)
                .orElseThrow(() -> new NotFoundException("출항 정보가 존재하지 않습니다."));
        departure.getShip().validateAuthority(member);
        reservationRepository.deleteAllByDeparture(departure);
        departureRepository.delete(departure);
    }

    public void delete(
            Departure departure
    ) {
        reservationRepository.deleteAllByDeparture(departure);
        departureRepository.deleteById(departure.getId());
    }
}