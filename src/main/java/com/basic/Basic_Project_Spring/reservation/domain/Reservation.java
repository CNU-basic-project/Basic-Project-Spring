package com.basic.Basic_Project_Spring.reservation.domain;

import com.basic.Basic_Project_Spring.common.exception.ForbiddenException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Reservation(Member member, Ship ship) {
        this.member = member;
        this.ship = ship;
    }

    public void validateAuthority(Member member) {
        if (!this.member.getId().equals(member.getId())) {
            throw new ForbiddenException("예약에 대한 권한이 없습니다.");
        }
    }
}
