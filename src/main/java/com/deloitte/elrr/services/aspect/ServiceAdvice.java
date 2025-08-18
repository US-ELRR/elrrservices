package com.deloitte.elrr.services.aspect;

import java.util.UUID;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * Log entity information.
     *
     * @param output the entity to log
     */
    private static void logEntityInfo(Entity output) throws Throwable {
        String clazz = output.getClass().getName();
        UUID id = output.getId();

        String username = getCurrentUsername();
        log.debug("Logging username: {}, entity: {}: {}", username, clazz,
                id);
        String json = Mapper.getMapper().writeValueAsString(output);
        log.debug(json);
    }

    /**
     * Get username from SecurityContext, returning "unknown" if not available.
     *
     * @return the username or "unknown"
     */
    private static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "unknown";
        }
        return authentication.getPrincipal().toString();
    }
}
