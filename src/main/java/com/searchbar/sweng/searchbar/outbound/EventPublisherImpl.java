package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Event.EventPublisher;
import com.searchbar.sweng.searchbar.model.Event.RezeptAddedEvent;
import com.searchbar.sweng.searchbar.model.Event.RezeptChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private StreamBridge streamBridge;

    @Autowired
    public EventPublisherImpl(StreamBridge streamBridge){
        this.streamBridge = streamBridge;
    }

    @Override
    public boolean publishEvent(RezeptAddedEvent event){
        var successfullyPublished = streamBridge.send("eventPublisher-out-0", event);
        if (successfullyPublished)
            LOGGER.info("Event published: " + event);
        else
            LOGGER.error("Publishing event failed: " + event);
        return successfullyPublished;
    }
    @Override
    public boolean publishEvent(RezeptChangedEvent event){
        var successfullyPublished = streamBridge.send("eventPublisher-out-0", event);
        if (successfullyPublished)
            LOGGER.info("Event published: " + event);
        else
            LOGGER.error("Publishing event failed: " + event);
        return successfullyPublished;
    }
}