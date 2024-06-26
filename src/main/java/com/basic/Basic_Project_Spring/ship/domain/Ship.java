package com.basic.Basic_Project_Spring.ship.domain;

import com.basic.Basic_Project_Spring.common.exception.ForbiddenException;
import com.basic.Basic_Project_Spring.member.domain.Member;
import com.basic.Basic_Project_Spring.ship.presentation.request.ShipRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imagePath;
    private int seats;
    private String type;
    private int speed;
    private double weight;
    private double length;
    private double width;
    private double height;
    private LocalDate launchDate;
    private LocalDate checkDate;

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
            LocalDate launchDate,
            LocalDate checkDate,
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
        this.launchDate = launchDate;
        this.checkDate = checkDate;
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
        launchDate = request.launchDate();
        checkDate = request.checkDate();
    }

    public void validateAuthority(Member member) {
        if (!owner.getId().equals(member.getId())) {
            throw new ForbiddenException("해당 배에 대한 권한이 없습니다.");
        }
    }
}
