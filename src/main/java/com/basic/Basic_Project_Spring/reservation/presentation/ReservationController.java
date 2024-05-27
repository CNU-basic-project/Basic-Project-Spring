package com.basic.Basic_Project_Spring.reservation.presentation;

import com.basic.Basic_Project_Spring.auth.Auth;
import com.basic.Basic_Project_Spring.common.response.Result;
import com.basic.Basic_Project_Spring.departure.application.DepartureService;
import com.basic.Basic_Project_Spring.reservation.application.ReservationService;
import com.basic.Basic_Project_Spring.reservation.domain.Reservation;
import com.basic.Basic_Project_Spring.reservation.presentation.request.ReservationRequest;
import com.basic.Basic_Project_Spring.reservation.presentation.response.ReservationResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final DepartureService departureService;

    @GetMapping
    public ResponseEntity<Result<List<ReservationResponse>>> get(
            @Auth Long memberId
    ) {
        List<Reservation> reservations = reservationService.getReservations(memberId);
        List<ReservationResponse> responses = reservations.stream()
                .map(ReservationResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(responses, responses.size()));
    }

    @PostMapping
    public ResponseEntity<Void> add(
            @Auth Long memberId,
            @Valid @RequestBody ReservationRequest request
    )  {
        Long reservationId = reservationService.add(memberId, request.departureId());
        return ResponseEntity.created(URI.create("/reservations/" + reservationId)).build();
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Auth Long memberId,
            @PathVariable("id") Long reservationId
    ) {
        reservationService.delete(memberId, reservationId);
    }
}
