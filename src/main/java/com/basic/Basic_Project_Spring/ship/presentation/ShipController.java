package com.basic.Basic_Project_Spring.ship.presentation;

import com.basic.Basic_Project_Spring.auth.Auth;
import com.basic.Basic_Project_Spring.common.response.Result;
import com.basic.Basic_Project_Spring.ship.application.ShipService;
import com.basic.Basic_Project_Spring.ship.domain.Ship;
import com.basic.Basic_Project_Spring.ship.presentation.request.ShipRequest;
import com.basic.Basic_Project_Spring.ship.presentation.response.ShipResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/ships")
@RestController
public class ShipController {

    private final ShipService shipService;

    @GetMapping
    public ResponseEntity<Result<List<ShipResponse>>> get(
            @Auth Long memberId
    ) {
        List<Ship> ships = shipService.getShips(memberId);
        List<ShipResponse> shipResponses = ships.stream()
                .map(ShipResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(shipResponses, shipResponses.size()));
    }

    @PostMapping
    public ResponseEntity<Void> add(
            @Auth Long memberid,
            @Valid @RequestBody ShipRequest request
    ) {
        Long shipId = shipService.add(memberid, request);
        return ResponseEntity.created(URI.create("/ships/" + shipId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @Auth Long memberId,
            @PathVariable("id") Long shipId,
            @Valid @RequestBody ShipRequest shipRequest
    ) {
        shipService.update(memberId, shipId, shipRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Auth Long memberId,
            @PathVariable("id") Long shipId
    ) {
        shipService.delete(memberId, shipId);
    }

}
