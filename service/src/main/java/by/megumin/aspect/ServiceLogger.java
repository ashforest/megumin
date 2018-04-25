package by.megumin.aspect;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j
public class ServiceLogger {

    @Pointcut("within(by.megumin.service..*)")
    public void serviceMethods(){}

    @Before(value = "serviceMethods()", argNames = "joinPoint")
    public void methodInvoke(JoinPoint joinPoint) {
        log.info("Call method: "
                + joinPoint.getSignature().toShortString()
                + " with args: "
                + Arrays.asList(joinPoint.getArgs()));
    }
}