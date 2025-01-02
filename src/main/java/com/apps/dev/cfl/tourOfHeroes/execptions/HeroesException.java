package com.apps.dev.cfl.tourOfHeroes.execptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HeroesException extends RuntimeException{

    private final HttpStatus statusCode;

    public HeroesException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
