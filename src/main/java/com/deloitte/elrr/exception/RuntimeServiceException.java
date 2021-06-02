/**
 * 
 */
package com.deloitte.elrr.exception;

/**
 * @author mnelakurti
 *
 */
public class RuntimeServiceException extends RuntimeException {

		private static final long serialVersionUID = -1L;
		
		public RuntimeServiceException(String erroMessage) {
			super(erroMessage);
		}
	}