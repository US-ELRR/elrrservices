/**
 *
 */
package com.deloitte.elrr.exception;

/**
 * @author mnelakurti
 *
 */
public class RuntimeServiceException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -1L;
    /**
     *
     * @param erroMessage
     */
    public RuntimeServiceException(final String erroMessage) {
        super(erroMessage);
    }
}
