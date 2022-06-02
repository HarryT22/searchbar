package com.searchbar.sweng.searchbar.outbound;

import com.searchbar.sweng.searchbar.model.Event.EventPublisher;
import com.searchbar.sweng.searchbar.model.Event.RezeptAddedEvent;
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

    /**
     * This method is used to publish the RezeptAddedEvent to rabbitMQ.
     * @param event the RezeptAddedEvent
     * @return boolean the publication was successful or not.
     */
    @Override
    public boolean publishEvent(RezeptAddedEvent event){
        var successfullyPublished = streamBridge.send("eventPublisher-out-1", event);
        if (successfullyPublished)
            LOGGER.info("Event published: " + event);
        else
            LOGGER.error("Publishing event failed: " + event);
        return successfullyPublished;
    }
}
