package com.chulung.cache;

public class CapacityOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = -603958967259976751L;

	public CapacityOutOfBoundsException() {
		super();
		
	}

	public CapacityOutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public CapacityOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CapacityOutOfBoundsException(String message) {
		super(message);
		
	}

	public CapacityOutOfBoundsException(Throwable cause) {
		super(cause);
		
	}

}
