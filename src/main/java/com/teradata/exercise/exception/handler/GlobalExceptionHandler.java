package com.teradata.exercise.exception.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teradata.exercise.common.APIErrorResponse;
import com.teradata.exercise.exception.AlbumNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler.class");
	
	@Value("${error.global.msg}") 
	private String globalErrorMsg;
	
	@Value("${error.parser.msg}") 
	private String parserErrorMsg;
	
	@Value("${error.constraintviolation.msg}") 
	private String constraintviolationMsg;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIErrorResponse> handleInvalidMethodArgument(HttpServletResponse response, MethodArgumentNotValidException e) throws IOException {
		logger.info("Caught an exception in GlobalExceptionHandler.handleInvalidMethodArgument() - ",e.getMessage());
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(getValidationErrorMessage(e));
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<APIErrorResponse> handleParserException(HttpServletResponse response, HttpMessageNotReadableException e) throws IOException {
		logger.error("Caught an exception in GlobalExceptionHandler.handleParserException() - ",e.getMessage());
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(parserErrorMsg + " - "+ e.getMessage());
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<APIErrorResponse> handleConstraintViolation(HttpServletResponse response, HttpMessageNotReadableException e) throws IOException {
		logger.error("Caught an exception in GlobalExceptionHandler.handleConstraintViolation() - ",e.getMessage());
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.CONFLICT.getReasonPhrase());
		error.setStatusCode(HttpStatus.CONFLICT.value());
		error.setMessage(constraintviolationMsg);
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.CONFLICT);	
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<APIErrorResponse> handleEmptyResult(HttpServletResponse response, Exception e) throws IOException {
		logger.error("Caught an exception in GlobalExceptionHandler.handleException() - ",e);
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("No data found");
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<APIErrorResponse> handleHttpMethodNotSupported(HttpServletResponse response, Exception e) throws IOException {
		logger.error("Caught an exception in GlobalExceptionHandler.handleException() - ",e);
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		error.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setMessage("Method not allowed");
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(AlbumNotFoundException.class)
	public ResponseEntity<APIErrorResponse> handleAlbumNotFoundException(HttpServletResponse response, Exception e) throws IOException {
		logger.debug("Caught an exception in GlobalExceptionHandler.handleException() - ",e);
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Album does not exist for the provided album id");
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIErrorResponse> handleException(HttpServletResponse response, Exception e) throws IOException {
		logger.error("Caught an exception in GlobalExceptionHandler.handleException() - ",e);
		APIErrorResponse error = new APIErrorResponse();
		error.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(globalErrorMsg);
		return new ResponseEntity<APIErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This method handles Exception, raised during the process of the request.
	 * @param validationException
	 * @return errorMessage
	 */
	private String getValidationErrorMessage(MethodArgumentNotValidException validationException) {
		List<String> list = new ArrayList<String>();
		StringBuffer errorMessage = new StringBuffer();
		String separator = "";
		try {
			List<ObjectError> errors = validationException.getBindingResult().getAllErrors();
			if(errors != null){
				for (ObjectError objectError : errors) {
					list.add(objectError.getDefaultMessage());
					errorMessage.append(separator);
					errorMessage.append(objectError.getDefaultMessage());
					separator= "; ";
				}
			}
		} catch (Exception e) {
			logger.warn("Exception occured in getValidationErrorMessage()", e);
		}
		return errorMessage.toString();
	}
}
