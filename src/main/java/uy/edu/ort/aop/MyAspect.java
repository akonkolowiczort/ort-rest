package uy.edu.ort.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uy.edu.ort.service.PersonaServiceImpl;

@Aspect
@Component
public class MyAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);

    @Pointcut("execution(* uy.edu.ort.service.*.*(..))")
    public void myArounPointcut() {
    }

    @Before("execution(* uy.edu.ort.controller.*.*(..))")
    public void actionBefore(JoinPoint joinPoint) {
        LOGGER.info("aop action before - method name : {}", joinPoint.getSignature().getName());
        LOGGER.info("aop action before - arguments   : {}", joinPoint.getArgs());
    }

    // @Around("@annotation(ExecutionLog)")
    @Around("myArounPointcut()")
    public Object logicAround(ProceedingJoinPoint pjp) {
        Object result = null;
        try {
            long init = System.currentTimeMillis();
            // invocar el metodo interceptado
            result = pjp.proceed();
            long time = System.currentTimeMillis() - init;
            LOGGER.info("Execution time => {} msecs", time);
        } catch (Throwable ex) {
            LOGGER.error("Error in around aspect ", ex);
            // manejar el error
        }
        return result;
    }

}
