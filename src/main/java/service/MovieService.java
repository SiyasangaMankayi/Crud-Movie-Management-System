
package com.example.moviemanagement.service;

import com.example.moviemanagement.exception.DuplicateMovieException;
import com.example.moviemanagement.entity.Movie;
import com.example.moviemanagement.repository.MovieRepository;
import com.example.moviemanagement.exception.DuplicateMovieException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {

        boolean exists = movieRepository.existsByTitleAndReleaseDateAndLocationAndCostAndCategory(
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getLocation(),
                movie.getCost(),
                movie.getCategory()
        );

        if (exists) {
            throw new DuplicateMovieException("This movie already exists");
        }

        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie updateMovie(Long id, Movie movie) {

        Movie existingMovie = getMovieById(id);

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setLocation(movie.getLocation());
        existingMovie.setCost(movie.getCost());
        existingMovie.setCategory(movie.getCategory());

        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }
}
