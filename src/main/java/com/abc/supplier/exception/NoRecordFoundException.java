package com.abc.supplier.exception;

public class NoRecordFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2163152545699177415L;

	public NoRecordFoundException (String exception) {
		super(exception);
	}
}
