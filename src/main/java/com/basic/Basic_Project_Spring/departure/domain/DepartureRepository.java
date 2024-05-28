package com.basic.Basic_Project_Spring.departure.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DepartureRepository extends JpaRepository<Departure, Long> {

    List<Departure> findAllByShipId(Long shipId);
    List<Departure> findAllByDeparturesContainingOrArrivalsContaining(String keyword1, String keyword2);
    List<Departure> findAllByDate(LocalDate date);

    @Query("SELECT s FROM Departure s WHERE s.date = :date AND (s.departures LIKE %:keyword% OR s.arrivals LIKE %:keyword%)")
    List<Departure> findAllByDateAndQuery(@Param("date") LocalDate date, @Param("keyword") String keyword);
}

//    String string = "2019-01-10";
//    LocalDate date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE);