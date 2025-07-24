package com.deloitte.elrr.services.aspect;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.elrr.entity.Entity;
import com.deloitte.elrr.util.Mapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ServiceAdvice {

    /**
     * Intercept Service Save calls and log the change.
     *
     * @param pjp
     * @return
     * @throws Throwable
     * @return Object being returned
     */
    @Around(value = "execution(* com.deloitte.elrr.jpa.svc.*.save(..))")
    public Entity aroundSave(ProceedingJoinPoint pjp) throws Throwable {
        Entity output = (Entity) pjp.proceed();
        String clazz = output.getClass().getName();
        UUID id = output.getId();
        log.warn("Logging {}: {}", clazz, id);
        String json = Mapper.getMapper().writeValueAsString(output);
        log.warn(json);
        return output;
    }
}
