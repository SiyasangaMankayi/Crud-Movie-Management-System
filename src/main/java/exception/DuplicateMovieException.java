package com.example.moviemanagement.exception;

public class DuplicateMovieException extends RuntimeException {

    public DuplicateMovieException(String message) {
        super(message);
    }
}