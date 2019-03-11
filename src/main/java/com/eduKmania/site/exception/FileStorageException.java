package com.eduKmania.site.exception;

public class FileStorageException extends RuntimeException {
	
	private String message;
	public FileStorageException(String message) {
		this.message = message;
	}
}
