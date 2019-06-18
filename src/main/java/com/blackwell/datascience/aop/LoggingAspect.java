package com.blackwell.datascience.aop;

import com.blackwell.datascience.ProbabilityTheoryUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger LOG = Logger.getLogger(ProbabilityTheoryUtils.class);

    @Pointcut("execution(public * com.com.blackwell.ProbabilityTheoryUtils.*(*))")
    public void utils() {}

    @Before("utils()")
    public void beforeUtils(JoinPoint joinPoint){
        LOG.info("Calling " + joinPoint.getSignature());
        LOG.info("Getting args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut="utils()", returning = "result")
    public void afterUtils(JoinPoint joinPoint, Object result){
        LOG.info("Finished " + joinPoint.getSignature());
        LOG.info("Result: " + result + "\n");
    }
}
