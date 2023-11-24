package com.bankapi.bankofmikaila.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 WILL NOT BE USED, WE ARE DOING SIMPLE LOGGER FOR EACH SERVICE CLASS. WILL BE LEAVING HERE IF ANY WANTS TO LOOK AT AOP ALTERNATIVE.
 */
//@Aspect
//@Component
//@Log4j2
//public class LoggerAspect {
//    @Pointcut("within(com.bankapi.bankofmikaila.controller.*)")
//    public void setPointcut() {
//        //serves as pointcut with @Pointcut
//    }
//
//    @Around("setPointcut()") //log before and after execution of a service method
//    @SneakyThrows
//    public Object logAroundExec(ProceedingJoinPoint proceedingJoinPoint) {
//        log.info("before {}", constructLogMsg(proceedingJoinPoint));
//        var proceed = proceedingJoinPoint.proceed();
//        log.info("after {} with result: {}", constructLogMsg(proceedingJoinPoint), proceed.toString());
//
//        return proceed;
//    }
//
//    //log when an exception (besides the exceptions thrown in the service (ONLY IN THE SERVICE, MethodArgumentNotValid and HttpMessageNotReadable exceptions are not logged bc they are not in the service).
//    @AfterThrowing(pointcut = "setPointcut()", throwing = "ex")
//    public void logAfterException(JoinPoint joinPoint, Exception ex) {
//        log.error("Exception during: {} with ex: {}", constructLogMsg(joinPoint), ex.getMessage());
//    }
//
//    //build the log message
//    private String constructLogMsg(JoinPoint joinPoint) {
//        var args = Arrays.asList(joinPoint.getArgs()).stream().map(String::valueOf).collect(Collectors.joining(",", " [", "]"));
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        String logBuilder = "executing " + method.getName() + ":" + args ;
//
//        return logBuilder;
//    }
//}
