package com.basic.Basic_Project_Spring.ship.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findAllByOwnerId(Long ownerId);
}
