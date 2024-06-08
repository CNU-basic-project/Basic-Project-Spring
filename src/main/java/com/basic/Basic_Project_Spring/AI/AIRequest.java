package com.basic.Basic_Project_Spring.AI;

public record AIRequest(
        double latitude,
        double longitude,
        double wave_height,
        double wind_speed,
        double wave_frequency
) {
}
