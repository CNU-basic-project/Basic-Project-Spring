package com.basic.Basic_Project_Spring.AI;

public record AIRequest(
        String latitude,
        String longitude,
        String wave_height,
        String wind_speed,
        String wave_frequency
) {
}
