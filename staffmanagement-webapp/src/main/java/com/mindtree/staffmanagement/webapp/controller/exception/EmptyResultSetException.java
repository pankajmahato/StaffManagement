package com.mindtree.staffmanagement.webapp.controller.exception;

public class EmptyResultSetException extends Exception {

	private static final long serialVersionUID = 6876783973693760994L;

	public EmptyResultSetException() {
		super();
	}

	public EmptyResultSetException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyResultSetException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyResultSetException(String message) {
		super(message);
	}

	public EmptyResultSetException(Throwable cause) {
		super(cause);
	}

}
