package com.basic.Basic_Project_Spring.ship.domain;

import com.basic.Basic_Project_Spring.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<Ship> findAllByOwner(Member owner);
}
