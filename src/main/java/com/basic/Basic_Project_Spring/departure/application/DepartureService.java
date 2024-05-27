package com.basic.Basic_Project_Spring.departure.application;

import com.basic.Basic_Project_Spring.common.exception.NotFoundException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.departure.domain.Departure;
import com.basic.Basic_Project_Spring.departure.domain.DepartureRepository;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.domain.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartureService {

    private final DepartureRepository departureRepository;
    private final ShipRepository shipRepository;
    private final MemberRepository memberRepository;

    public List<Departure> getDepartures() {
        return departureRepository.findAll();
    }

    public Long add(
            LocalDate date,
            String departures,
            LocalTime departureTIme,
            String arrivals,
            LocalTime arrivalTime,
            int price,
            Long memberId,
            Long shipId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new NotFoundException("배 정보가 존재하지 않습니다."));
        ship.validateAuthority(member);
        Departure departure = new Departure(
                date,
                departures,
                departureTIme,
                arrivals,
                arrivalTime,
                price,
                0,
                ship
        );
        return departureRepository.save(departure).getId();
    }

    public void delete(
            Long memberId,
            Long shipId,
            Long departureId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new NotFoundException("배 정보가 존재하지 않습니다."));
        ship.validateAuthority(member);

    }

}
