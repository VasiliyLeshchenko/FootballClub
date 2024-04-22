package com.footballclubapplication.www.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Producer<?,?>) {
            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setTarget(bean);
            proxyFactory.addAdvice(new AfterReturningAdvice() {
                   @Override
                   public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                       if (method.getName().equals("send")) {
                           log.info("SEND MESSAGE TO KAFKA");
                       }

                   }
               }
            );
            return proxyFactory.getProxy();
        }

        return bean;
    }
}
