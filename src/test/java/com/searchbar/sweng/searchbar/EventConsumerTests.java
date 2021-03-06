package com.searchbar.sweng.searchbar;

import com.searchbar.sweng.searchbar.model.Event.UserRegisteredEvent;
import com.searchbar.sweng.searchbar.model.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(TestChannelBinderConfiguration.class)
public class EventConsumerTests {

    @MockBean
    private EmailService emailService;

    @Autowired
    private InputDestination inputDestination;


    /**
     * Tests that the emailService is called with the right parameters
     */
    @Test
    void testConsumeMessage() {
        this.inputDestination.send(
                MessageBuilder
                        .withPayload(new UserRegisteredEvent(2,"harry","harry.thuente@gmx.net"))
                        .build()
        );
        verify(this.emailService, times(1)).rezeptOfTheDay("harry.thuente@gmx.net","harry");
    }
}
