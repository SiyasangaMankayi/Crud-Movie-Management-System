package com.example.moviemanagement.repository;

import com.example.moviemanagement.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByTitleAndReleaseDateAndLocationAndCostAndCategory(
            String title,
            LocalDate releaseDate,
            String location,
            BigDecimal cost,
            String category
    );
}