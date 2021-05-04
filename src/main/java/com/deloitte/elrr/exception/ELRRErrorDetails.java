/**
 * 
 */
package com.deloitte.elrr.exception;

import java.util.Date;
import java.util.List;

/**
 * @author mnelakurti
 *
 */
public class ELRRErrorDetails {

	private Date timestamp;
	private String message;
	private String path;
	private List<String> details;

	public ELRRErrorDetails(Date timestamp, String message, String path, List<String> details) {
			super();
			this.timestamp = timestamp;
			this.message = message;
			this.path = path;
			this.details = details;
		}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public List<String> getDetails() {
		return details;
	}
}