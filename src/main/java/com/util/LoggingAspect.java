package com.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("within(com.controller..*)")
    public Object aroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("logging started");
        Long startTime = System.currentTimeMillis();
        String method = joinPoint.getSignature().getName();

        //before method execution
        logger.info("{} method started", method);
        Object proceed = joinPoint.proceed();

        //after method execution
        logger.info("{} method executed", method);

        Long endTime = System.currentTimeMillis();

        //logging ended
        logger.info("logging ended in {} ms", endTime - startTime);
        return proceed;
    }


}




