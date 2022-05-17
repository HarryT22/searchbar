package com.searchbar.sweng.searchbar.inbound;

import com.searchbar.sweng.searchbar.model.Event.UserRegisteredEvent;
import com.searchbar.sweng.searchbar.model.RezepteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

public class EventConsumer implements Consumer<UserRegisteredEvent> {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private RezepteService rezepteService;

    @Autowired
    public EventConsumer(RezepteService rezepteService) {
        this.rezepteService = rezepteService;
    }

    @Override
    public void accept(UserRegisteredEvent userRegisteredEvent) {
        LOG.info("Consumed Event: " + userRegisteredEvent);
    }
}
