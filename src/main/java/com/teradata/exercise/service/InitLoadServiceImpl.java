package com.teradata.exercise.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teradata.exercise.client.ServiceInvoker;
import com.teradata.exercise.common.Utility;
import com.teradata.exercise.db.entity.Album;
import com.teradata.exercise.db.entity.Photo;
import com.teradata.exercise.db.repository.AlbumRepository;
import com.teradata.exercise.db.repository.PhotoRepository;
import com.teradata.exercise.vo.AlbumVO;
import com.teradata.exercise.vo.PhotoVO;

@Service
public class InitLoadServiceImpl implements InitLoadService{
	private final Logger logger = LoggerFactory.getLogger(InitLoadServiceImpl.class);
	
	@Autowired
	ServiceInvoker serviceInvoker;
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Transactional
	public void loadData() throws InterruptedException, ExecutionException{
		// make call to ServiceInvoker 
		
		Future<AlbumVO[]> albumsFuture = serviceInvoker.getAlbums();
		Future<PhotoVO[]> photosFuture = serviceInvoker.getPhotos();
		AlbumVO[] albums = albumsFuture.get();
		PhotoVO[] photos = photosFuture.get();
		
		logger.info("no of photos to be loaded - "+ photos.length);
		logger.info("no of albums to be loaded - "+ albums.length);
		
		Album album = new Album();
		// load the albums into the database
		for(AlbumVO albumVo:albums){
			Utility.copyProperties(albumVo, album);
			albumRepository.save(album);
		}
		
		// load the photos into the database
		Photo photo = new Photo();
		for(PhotoVO photoVo: photos){
			Utility.copyProperties(photoVo, photo);
			photoRepository.save(photo);
		}
		
	}
}
