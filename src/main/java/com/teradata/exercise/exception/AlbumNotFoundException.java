package com.teradata.exercise.exception;

public class AlbumNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlbumNotFoundException(String message){
		super(message);
	}
}
