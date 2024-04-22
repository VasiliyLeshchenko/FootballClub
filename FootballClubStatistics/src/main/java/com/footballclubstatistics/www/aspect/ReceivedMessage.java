package com.footballclubstatistics.www.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ReceivedMessage {
    private final Counter counter;


    public ReceivedMessage(MeterRegistry registry) {
        this.counter = Counter.builder("received_messages")
                .description("Number of received messages from Kafka")
                .register(registry);
    }

    @After("@annotation(com.footballclubstatistics.www.annotation.ReceiverMessage) ")
    public void after() {
        log.info("Received message {}", counter.count());
        counter.increment();
    }
}
