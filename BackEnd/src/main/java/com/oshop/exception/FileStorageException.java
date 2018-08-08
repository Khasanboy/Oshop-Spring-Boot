package com.oshop.exception;

public class FileStorageException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8637161699162034895L;

	public FileStorageException(String message) {
		super(message);
	}
	
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
