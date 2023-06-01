package com.chiendv.examplespringaop.configuration;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Configuration
public class AopHandler {
    HttpServletRequest request;
    @Autowired
    public AopHandler(HttpServletRequest request) {
        this.request = request;
    }

    // ở đây a cắt theo tùng method
    @Pointcut("within(com.chiendv.examplespringaop.controller..*)"
            + " || within(com.chiendv.examplespringaop.service..*)")
    public void cutMethod() {}

    @Before("cutMethod()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Trước khi vào method sẽ vào Before");
        String requestBody = "";
        if(joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            requestBody = Arrays.toString(joinPoint.getArgs());
        }
        System.out.println("PATH: " + request.getRequestURI());
        System.out.println("Log được ghi ở AOP: " + requestBody);
    }

    @AfterReturning(pointcut = "cutMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("Xử lý xong method sẽ vào AfterReturning");
        final String responseBody = result.toString();
        System.out.println("ResponseBody được ghi ở AOP: " + responseBody);
    }

    @AfterThrowing(pointcut = "cutMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        System.out.println("Có Exception sẽ vào AfterThrowing");
        String error = ExceptionUtils.getStackTrace(exception);
        System.out.println("Error Exception: " + error);
    }

}
