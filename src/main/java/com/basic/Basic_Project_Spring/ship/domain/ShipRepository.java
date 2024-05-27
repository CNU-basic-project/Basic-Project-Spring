package com.basic.Basic_Project_Spring.ship.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    List<Ship> findAllByMemberId(Long id);
}
