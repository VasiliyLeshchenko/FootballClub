package com.footballclubapplication.www.producer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CustomProducerAspect {

    @After("execution(* com.footballclubapplication.www.producer.CustomProducer.send(..))")
    public void send() {
        log.info("Aspect send message");
    }

    @Pointcut("execution(* com.footballclubapplication.www.producer.CustomProducer.send(..))")
    public void sendStatistics() {
    }

    @After("sendStatistics()")
    public void trySend() {
        log.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    }
}
