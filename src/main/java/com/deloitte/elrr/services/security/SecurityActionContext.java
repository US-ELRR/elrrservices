package com.deloitte.elrr.services.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Request-scoped bean for storing the current security action and resource
 * being evaluated. This allows aspects and other components to access
 * the action and resource that triggered a permission check within the same
 * request.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecurityActionContext {

    private String currentAction;
    private String currentResource;

    /**
     * Set the current action and resource for this request.
     *
     * @param action the action being performed
     * @param resource the resource being accessed
     */
    public void setCurrentContext(String action, String resource) {
        this.currentAction = action;
        this.currentResource = resource;
    }

    /**
     * Get the current action for this request.
     *
     * @return the current action, or null if none is set
     */
    public String getCurrentAction() {
        return this.currentAction;
    }

    /**
     * Get the current resource for this request.
     *
     * @return the current resource, or null if none is set
     */
    public String getCurrentResource() {
        return this.currentResource;
    }
}
