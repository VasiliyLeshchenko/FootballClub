package com.footballclubapplication.www.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendStatisticsPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishStatistics(String message) {
        log.info("Publish statistics: {}", message);
        applicationEventPublisher.publishEvent(new SendStatisticsEvent(message));
    }
}
