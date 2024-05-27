package com.basic.Basic_Project_Spring.ship.domain;

import com.basic.Basic_Project_Spring.common.exception.ForbiddenException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.ship.presentation.request.ShipRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String imagePath;
    private int seats; // 정원
    private String type;
    private int speed;
    private double weight;
    private double length;
    private double width;
    private double height;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Member owner;

    public Ship(
            String name,
            String imagePath,
            int seats,
            String type,
            int speed,
            double weight,
            double length,
            double width,
            double height,
            Member owner
    ) {
        this.name = name;
        this.imagePath = imagePath;
        this.seats = seats;
        this.type = type;
        this.speed = speed;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.owner = owner;
    }

    public Ship(ShipRequest request, Member owner) {
        update(request);
        this.owner = owner;
    }

    public void update(ShipRequest request) {
        name = request.name();
        imagePath = request.imagePath();
        seats = request.seats();
        type = request.type();
        speed = request.speed();
        weight = request.weight();
        length = request.length();
        width = request.width();
        height = request.height();
    }

    public void validateAuthority(Member member) {
        if (!owner.getId().equals(member.getId())) {
            throw new ForbiddenException("해당 배에 대한 권한이 없습니다.");
        }
    }
}
