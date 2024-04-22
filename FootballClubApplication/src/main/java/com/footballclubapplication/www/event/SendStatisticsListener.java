package com.footballclubapplication.www.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendStatisticsListener {
    @EventListener
    public void sendStatistics(SendStatisticsEvent sendStatisticsEvent) {
        log.info("Send statistics event: {}", sendStatisticsEvent.getMessage());
    }
}
