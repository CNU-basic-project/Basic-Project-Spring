package com.basic.Basic_Project_Spring.ship.presentation.response;

import com.basic.Basic_Project_Spring.member.presentation.response.MemberResponse;
import com.basic.Basic_Project_Spring.ship.domain.Ship;

public record ShipResponse(
        String name,
        String imagePath,
        int seats,
        String type,
        int speed,
        double weight,
        double length,
        double width,
        double height,
        MemberResponse owner
) {

    public static ShipResponse of(Ship ship) {
        return new ShipResponse(
                ship.getName(),
                ship.getImagePath(),
                ship.getSeats(),
                ship.getType(),
                ship.getSpeed(),
                ship.getWeight(),
                ship.getLength(),
                ship.getWidth(),
                ship.getHeight(),
                new MemberResponse(ship.getOwner().getName())
        );
    }
}
