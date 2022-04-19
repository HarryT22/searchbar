package com.searchbar.sweng.searchbar.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchRecipieException extends RuntimeException{
    public NoSuchRecipieException(String message){super(message);}
}
