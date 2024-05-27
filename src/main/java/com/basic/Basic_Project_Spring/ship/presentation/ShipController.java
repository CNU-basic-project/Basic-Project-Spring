package com.basic.Basic_Project_Spring.ship.presentation;

import com.basic.Basic_Project_Spring.common.response.Result;
import com.basic.Basic_Project_Spring.ship.application.ShipService;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.presentation.response.ShipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/ships")
@RestController
public class ShipController {

    private final ShipService shipService;

    @GetMapping
    public ResponseEntity<Result<List<ShipResponse>>> get() {
        List<Ship> ships = shipService
    }

}
