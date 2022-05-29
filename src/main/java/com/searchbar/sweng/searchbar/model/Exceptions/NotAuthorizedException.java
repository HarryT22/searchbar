package com.searchbar.sweng.searchbar.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotAuthorizedException extends Throwable {
    public NotAuthorizedException(String s) {
    }
}
