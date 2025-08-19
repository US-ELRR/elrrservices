package com.deloitte.elrr.services.aspect;

import java.util.UUID;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.deloitte.elrr.entity.Entity;
import com.deloitte.elrr.util.Mapper;
import com.deloitte.elrr.services.security.SecurityActionContext;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ServiceAdvice {

    @Autowired
    private SecurityActionContext securityActionContext;

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
    @SuppressWarnings("unchecked")
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
     * Intercept Service Delete calls and log the deletion.
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.deloitte.elrr.jpa.svc.*.delete(..))")
    public void aroundDelete(ProceedingJoinPoint pjp) throws Throwable {
        // get the ID being deleted before the operation
        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            Object id = args[0];
            logDeleteInfo(id, pjp.getTarget().getClass().getSimpleName());
        }

        // perform operation
        pjp.proceed();
    }

    /**
     * Log entity information.
     *
     * @param output the entity to log
     */
    private void logEntityInfo(Entity output) throws Throwable {
        String clazz = output.getClass().getName();
        UUID id = output.getId();

        String username = getCurrentUsername();
        String action = securityActionContext.getCurrentAction();
        String resource = securityActionContext.getCurrentResource();
        UUID requestId = securityActionContext.getRequestId();
        log.info("Logging requestId: {}, username: {}, action: {}, "
                + "resource: {}, entity: {}: {}", requestId, username, action,
                resource, clazz, id);
        String json = Mapper.getMapper().writeValueAsString(output);
        log.debug(json);
    }

    /**
     * Log delete operation information.
     *
     * @param id the ID of the entity being deleted
     * @param serviceClass the service class performing the deletion
     */
    private void logDeleteInfo(Object id, String serviceClass)
            throws Throwable {
        String username = getCurrentUsername();
        String action = securityActionContext.getCurrentAction();
        String resource = securityActionContext.getCurrentResource();
        UUID requestId = securityActionContext.getRequestId();
        log.info("Logging requestId: {}, username: {}, action: {}, "
                + "resource: {}, service: {}, deleting entity with id: {}",
                requestId, username, action, resource, serviceClass, id);
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
