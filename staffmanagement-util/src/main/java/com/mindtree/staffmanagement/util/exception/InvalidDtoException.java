package com.mindtree.staffmanagement.util.exception;

public class InvalidDtoException extends Exception {

	private static final long serialVersionUID = -8123242820622047071L;

	public InvalidDtoException() {
		super();
	}

	public InvalidDtoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidDtoException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDtoException(String message) {
		super(message);
	}

	public InvalidDtoException(Throwable cause) {
		super(cause);
	}

}
