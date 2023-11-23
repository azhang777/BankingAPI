package com.bankapi.bankofmikaila.logger;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Log4j2
public class LoggerAspect {
    private static final String POINTCUT = "within(com.bankapi.bankofmikaila.service.*)"; //log every method within the service package

    @Around(POINTCUT) //log before and after execution of a service method
    @SneakyThrows
    public Object logAroundExec(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("before {}", constructLogMsg(proceedingJoinPoint));
        var proceed = proceedingJoinPoint.proceed();
        log.info("after {} with result: {}", constructLogMsg(proceedingJoinPoint), proceed.toString());

        return proceed;
    }

    //log when an exception (besides the exceptions thrown in the service (ONLY IN THE SERVICE, MethodArgumentNotValid and HttpMessageNotReadable exceptions are not logged bc they are not in the service).
    @AfterThrowing(pointcut = POINTCUT, throwing = "ex")
    public void logAfterException(JoinPoint joinPoint, Exception ex) {
        log.error("Exception during: {} with ex: {}", constructLogMsg(joinPoint), ex.getMessage());
    }

    //build the log message
    private String constructLogMsg(JoinPoint joinPoint) {
        var args = Arrays.asList(joinPoint.getArgs()).stream().map(String::valueOf).collect(Collectors.joining(",", " [", "]"));
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String logBuilder = "executing " + method.getName() + ":" + args ;

        return logBuilder;
    }
}
