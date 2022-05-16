package com.searchbar.sweng.searchbar.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NonValidFileException extends RuntimeException{
    public NonValidFileException(String message){super(message);}
}
