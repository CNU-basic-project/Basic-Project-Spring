package com.basic.Basic_Project_Spring.reservation.application;

import com.basic.Basic_Project_Spring.common.exception.NotFoundException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.reservation.domain.Reservation;
import com.basic.Basic_Project_Spring.reservation.domain.ReservationRepository;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.domain.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShipRepository shipRepository;
    private final MemberRepository memberRepository;

    public Long add(
            Long memberId,
            Long shipId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new NotFoundException("배 정보가 존재하지 않습니다."));
        Reservation reservation = new Reservation(member, ship);
        return reservationRepository.save(reservation).getId();
    }

    public void delete(
            Long memberId,
            Long reservationId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        // TODO findByMemberIdAndShipId 는?
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow();
        reservationRepository.delete(reservation);
    }
}
