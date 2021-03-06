package com.searchbar.sweng.searchbar.inbound;

import com.searchbar.sweng.searchbar.model.Service.EmailService;
import com.searchbar.sweng.searchbar.model.Event.UserRegisteredEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EventConsumer implements Consumer<UserRegisteredEvent> {

    private EmailService emailService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public EventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Accepts a UserRegisteredEvent and calls a function in my EmailService class.
     * @param userRegisteredEvent with the data passed by the Login and Registration Microservice.
     */
    @Override
    public void accept(UserRegisteredEvent userRegisteredEvent) {
        LOGGER.info("Consumed Event: " + userRegisteredEvent);
        this.emailService.rezeptOfTheDay(
                userRegisteredEvent.getEmail(),
                userRegisteredEvent.getName()
        );
    }
}
