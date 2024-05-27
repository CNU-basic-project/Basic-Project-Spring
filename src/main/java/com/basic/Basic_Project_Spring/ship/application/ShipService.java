package com.basic.Basic_Project_Spring.ship.application;

import com.basic.Basic_Project_Spring.auth.Auth;
import com.basic.Basic_Project_Spring.common.exception.NotFoundException;
import com.basic.Basic_Project_Spring.common.exception.UnAuthorizeException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.member.domain.MemberRepository;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.domain.ShipRepository;
import com.basic.Basic_Project_Spring.ship.presentation.request.ShipRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final MemberRepository memberRepository;

    public List<Ship> getShips(
            @Auth Long memberId
    ) {
        return shipRepository.findAllByMemberId(memberId);
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
        shipRepository.delete(ship);
    }
}