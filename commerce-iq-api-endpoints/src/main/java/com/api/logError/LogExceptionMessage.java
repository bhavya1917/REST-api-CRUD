package com.api.logError;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
@Configuration
public class LogExceptionMessage extends Exception {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private HttpStatus status;
	@Getter
	@Setter
	private String message;

	public static LogExceptionMessage status(HttpStatus status) {
		LogExceptionMessage exception = new LogExceptionMessage();
		exception.setStatus(status);
		return exception;
	}

	public static LogExceptionMessage error(String message) {
		LogExceptionMessage exception = new LogExceptionMessage();
		exception.setMessage(message);
		return exception;
	}

	public LogExceptionMessage message(String message) {
		this.setMessage(message);
		return this;
	}
}
