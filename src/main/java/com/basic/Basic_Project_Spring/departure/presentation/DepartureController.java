package com.basic.Basic_Project_Spring.departure.presentation;

import com.basic.Basic_Project_Spring.auth.Auth;
import com.basic.Basic_Project_Spring.common.response.Result;
import com.basic.Basic_Project_Spring.departure.application.DepartureService;
import com.basic.Basic_Project_Spring.departure.domain.Departure;
import com.basic.Basic_Project_Spring.departure.presentation.request.DepartureRequest;
import com.basic.Basic_Project_Spring.departure.presentation.response.DepartureResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/departures")
@RestController
public class DepartureController {

    private final DepartureService departureService;
    @GetMapping
    public ResponseEntity<Result<List<DepartureResponse>>> get() {
        List<Departure> departures = departureService.getDepartures();
        List<DepartureResponse> responses = departures.stream()
                .map(DepartureResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(responses, responses.size()));
    }

    @GetMapping
    public ResponseEntity<Result<List<DepartureResponse>>> getBySearch(
            @RequestParam String keyword
    ) {
        List<Departure> departures = departureService.getBySearch(keyword);
        List<DepartureResponse> responses = departures.stream()
                .map(DepartureResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(responses, responses.size()));
    }

    @GetMapping
    public ResponseEntity<Result<List<DepartureResponse>>> getByDate(
            @RequestParam String date
    ) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Departure> departures = departureService.getByDate(localDate);
        List<DepartureResponse> responses = departures.stream()
                .map(DepartureResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(responses, responses.size()));
    }

    @GetMapping
    public ResponseEntity<Result<List<DepartureResponse>>> getByQuery(
            @RequestParam String date,
            @RequestParam String keyword
    ) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        List<Departure> departures = departureService.getByQuery(localDate, keyword);
        List<DepartureResponse> responses = departures.stream()
                .map(DepartureResponse::of)
                .toList();
        return ResponseEntity.ok().body(new Result<>(responses, responses.size()));
    }

    @PostMapping
    public ResponseEntity<Void> add(
            @Auth Long memberId,
            @Valid @RequestBody DepartureRequest request
    ) {
        Long departureId = departureService.add(memberId, request);
        return ResponseEntity.created(URI.create("/departures/" + departureId)).build();
    }

    @DeleteMapping("/{id}")
    public void delete(
            @Auth Long memberId,
            @PathVariable("id") Long departureId
    ) {
        departureService.delete(memberId, departureId);
    }
}
