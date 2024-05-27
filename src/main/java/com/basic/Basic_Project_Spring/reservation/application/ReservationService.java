package com.basic.Basic_Project_Spring.reservation.application;

import com.basic.Basic_Project_Spring.common.exception.NotFoundException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.departure.domain.Departure;
import com.basic.Basic_Project_Spring.departure.domain.DepartureRepository;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.reservation.domain.Reservation;
import com.basic.Basic_Project_Spring.reservation.domain.ReservationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final DepartureRepository departureRepository;
    private final MemberRepository memberRepository;

    public List<Reservation> getReservations(Long memberId) {
        return reservationRepository.findAllByMemberId(memberId);
    }

    public Long add(
            Long memberId,
            Long departureId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Departure departure = departureRepository.findById(departureId)
                .orElseThrow(() -> new NotFoundException("출항 정보가 존재하지 않습니다."));
        departure.reservation();
        Reservation reservation = new Reservation(member, departure);
        return reservationRepository.save(reservation).getId();
    }

    public void delete(
            Long memberId,
            Long reservationId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("예약 정보가 존재하지 않습니다."));
        reservation.validateAuthority(member);
        reservation.getDeparture().cancel();
        reservationRepository.delete(reservation);
    }
}
