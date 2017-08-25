package com.teradata.exercise.client;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.teradata.exercise.vo.AlbumVO;
import com.teradata.exercise.vo.PhotoVO;

@Component
public class ServiceInvoker {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${data.albums.url}")
	String albumUrl;
	
	@Value("${data.photos.url}")
	String photoUrl;
	
	@Async
	public Future<AlbumVO[]> getAlbums(){
		AlbumVO[] albums = restTemplate.getForObject(albumUrl, AlbumVO[].class);
		return new AsyncResult<>(albums);
	}
	
	@Async
	public Future<PhotoVO[]> getPhotos(){
		PhotoVO[] photos = restTemplate.getForObject(photoUrl, PhotoVO[].class);
		return new AsyncResult<>(photos);
	}
}
