package com.deloitte.elrr.services.aspect;

import java.util.UUID;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.deloitte.elrr.entity.Auditable;
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
        // get entity
        Auditable<String> input = (Auditable<String>) pjp.getArgs()[0];
        // set username
        setUpdatedByUsername(input, getUsername());
        // perform operation
        Entity output = (Entity) pjp.proceed();
        // log
        logEntityInfo(output);

        return output;
    }

    /**
     * Intercept Service SaveAll calls and log the changes.
     *
     * @param pjp
     * @return
     * @throws Throwable
     * @return Collection of entities being returned
     */
    @Around(value = "execution(* com.deloitte.elrr.jpa.svc.*.saveAll(..))")
    public Collection<Entity> aroundSaveAll(ProceedingJoinPoint pjp)
            throws Throwable {
        // get entities
        Collection<Auditable<String>> inputs =
        (Collection<Auditable<String>>) pjp.getArgs()[0];
        // set username
        inputs.forEach(input -> setUpdatedByUsername(input, getUsername()));
        // perform operation
        Collection<Entity> outputs = (Collection<Entity>) pjp.proceed();
        // log
        outputs.forEach(output -> {
            try {
                logEntityInfo(output);
            } catch (Throwable e) {
                log.error("Error logging entity info", e);
            }
        });

        return outputs;
    }

    /**
     * Set entity updatedBy.
     *
     * @param input    the auditable entity
     * @param username the username to set
     */
    private static void setUpdatedByUsername(Auditable<String> input,
            String username) {
        input.setUpdatedBy(username);
    }

    /**
     * Log entity information.
     *
     * @param output the entity to log
     */
    private static void logEntityInfo(Entity output) throws Throwable {
        String clazz = output.getClass().getName();
        UUID id = output.getId();

        log.info("Logging username: {}, entity: {}: {}", getUsername(), clazz,
                id);
        String json = Mapper.getMapper().writeValueAsString(output);
        log.info(json);
    }

    /**
     * Get username from SecurityContext.
     *
     * @return the username
     */
    private static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException(
                    "No authentication found for auditing.");
        }
        return authentication.getPrincipal().toString();
    }
}
