package com.teradata.exercise.common;

public class APIErrorResponse {
	
	// Holds http status message
	private String statusMessage;
	
	// Holds http status code
	private int statusCode;
	
	// Holds error message
	private String message;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
