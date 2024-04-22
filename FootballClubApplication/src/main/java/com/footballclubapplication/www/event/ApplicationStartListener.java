package com.footballclubapplication.www.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationStartListener {
    @EventListener
    public void contextInitialized(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Application ready");
    }
}
