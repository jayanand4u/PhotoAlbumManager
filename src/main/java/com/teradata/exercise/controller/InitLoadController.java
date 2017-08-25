package com.teradata.exercise.controller;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teradata.exercise.service.InitLoadService;

/**
 * Exposes 'init' endpoint to initialize users, albums and photos from the third party site.
 * @author J
 *
 */
@RestController
public class InitLoadController {
	private final Logger logger = LoggerFactory.getLogger(InitLoadController.class);
	
	@Autowired
	InitLoadService initLoadService;
	
	/**
	 * Retrieves the users, albums and photos from the third party and then store them in the data source.
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@RequestMapping(value="/v1/init", method = {RequestMethod.GET,RequestMethod.POST},produces={MediaType.APPLICATION_JSON_VALUE})
	public void init() throws InterruptedException, ExecutionException{
		initLoadService.loadData();
	}

}
