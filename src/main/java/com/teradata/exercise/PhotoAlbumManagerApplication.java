package com.teradata.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(ignoreResourceNotFound=true, value={"classpath:api.properties", "classpath:api-${spring.profiles.active}.properties"})
public class PhotoAlbumManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAlbumManagerApplication.class, args);
	}
}
