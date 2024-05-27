package com.basic.Basic_Project_Spring.reservation.domain;

import com.basic.Basic_Project_Spring.common.exception.ForbiddenException;
import com.basic.Basic_Project_Spring.departure.domain.Departure;
import com.basic.Basic_Project_Spring.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JoinColumn(name = "departure_id")
    private Departure departure;

    public Reservation(Member member, Departure departure) {
        this.member = member;
        this.departure = departure;
    }

    public void validateAuthority(Member member) {
        if (!this.member.getId().equals(member.getId())) {
            throw new ForbiddenException("해당 예약에 대한 권한이 없습니다.");
        }
    }
}
