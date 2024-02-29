package com.gateway.app.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class PerformanceAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    /*
    @Around("execution(* com.gateway.app.services..*(..)) && target(bean)") //execute in the whole service package
    public Object logStartAndEnd(ProceedingJoinPoint jp, Object bean) throws Throwable {
        final String className = bean.getClass().getSimpleName();
        final String methodName = jp.getSignature().getName();
        final Object[] args = jp.getArgs();
        logger.info("Starting method: {} in class: {} with args: {}", methodName, className, args);
        final Object result = jp.proceed();
        if (Objects.nonNull(result)) {
            logger.info("End method: {} in class: {} return value: {}", methodName, className, result);
        } else {
            logger.info("End void method: {} in class: {}", methodName, className);
        }
        return result;
    }

     */


    @Around("@annotation(LogExecutionTime) && target(bean)") //execute by @LogExecutionTime
    public Object logPerformance(ProceedingJoinPoint jp, Object bean) throws Throwable {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = jp.proceed();
        stopWatch.stop();
        final long executionTime = stopWatch.getTotalTimeMillis();
        final String methodName = jp.getSignature().getName();
        final String className = bean.getClass().getSimpleName();
        logger.info("Execution time {} ms for method: {} in class: {}", executionTime, methodName, className);
        return result;
    }

}
