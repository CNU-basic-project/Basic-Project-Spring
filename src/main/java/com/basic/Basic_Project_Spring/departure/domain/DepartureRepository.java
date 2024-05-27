package com.basic.Basic_Project_Spring.departure.domain;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartureRepository extends JpaRepository<Departure, Long> {

    List<Departure> findAllByDeparturesContainingOrArrivalsContaining(String keyword1, String keyword2);
    List<Departure> findAllByDate(LocalDate date);

    @Query("SELECT s FROM Ship s WHERE s.date = :date AND (s.departures LIKE :keyword OR s.arrivals LIKE :keyword)")
    List<Departure> findAllByDateAndQuery(LocalDate date, String keyword);
}

//    String string = "2019-01-10";
//    LocalDate date = LocalDate.parse(string, DateTimeFormatter.ISO_DATE);