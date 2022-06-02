package com.searchbar.sweng.searchbar.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class MessageNotSendException extends RuntimeException{
    public MessageNotSendException(String message){super(message);}
}
