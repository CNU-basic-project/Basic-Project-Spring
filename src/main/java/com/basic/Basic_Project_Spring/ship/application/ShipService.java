package com.basic.Basic_Project_Spring.ship.application;

import com.basic.Basic_Project_Spring.common.exception.NotFoundException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.departure.application.DepartureService;
import com.basic.Basic_Project_Spring.departure.domain.DepartureRepository;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.domain.ShipRepository;
import com.basic.Basic_Project_Spring.ship.presentation.request.ShipRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final DepartureRepository departureRepository;
    private final MemberRepository memberRepository;
    private final DepartureService departureService;

    public List<Ship> getShips(
            Long memberId
    ) {
        Member owner = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        return shipRepository.findAllByOwner(owner);
    }

    public Long add(
            Long memberId,
            ShipRequest request
    ) {
        Member owner = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = new Ship(
                request.name(),
                request.imagePath(),
                request.seats(),
                request.type(),
                request.speed(),
                request.weight(),
                request.length(),
                request.width(),
                request.height(),
                request.launchDate(),
                owner
        );
        return shipRepository.save(ship).getId();
    }

    public void update(
            Long memberId,
            Long shipId,
            ShipRequest request
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new NotFoundException("배 정보가 존재하지 않습니다."));
        ship.validateAuthority(member);
        ship.update(request);
        shipRepository.save(ship);
    }

    public void delete(
            Long memberId,
            Long shipId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UnAuthorizeException("회원 정보가 존재하지 않습니다."));
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new NotFoundException("배 정보가 존재하지 않습니다."));
        ship.validateAuthority(member);
        departureRepository.findAllByShip(ship)
                .forEach(departureService::delete);
        shipRepository.delete(ship);
    }
}