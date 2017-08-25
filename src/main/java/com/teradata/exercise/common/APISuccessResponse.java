package com.teradata.exercise.common;

/**
 * Model class for returning the success response for create/update API call. 
 * @author J
 *
 */
public class APISuccessResponse {
	String message;
	String id;
	
	public APISuccessResponse(String message, String id) {
		super();
		this.message = message;
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
