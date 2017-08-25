package com.teradata.exercise.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teradata.exercise.common.KeyGenUtil;
import com.teradata.exercise.common.Utility;
import com.teradata.exercise.db.entity.Photo;
import com.teradata.exercise.db.repository.PhotoRepository;
import com.teradata.exercise.vo.PhotoVO;

/**
 * Photo Service implementation.
 * @author J
 *
 */
@Service
public class PhotoServiceImpl implements PhotoService{
	private final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);
	
	@Autowired
	PhotoRepository photoRepository;	
	
	@Autowired
	KeyGenUtil keyGenUtil;
	
	/**
	 * Returns all photos in an album
	 */
	@Override
	@Transactional(readOnly=true)
	public List<PhotoVO> getPhotos(String albumId) {
		Iterable<Photo> photos = photoRepository.findByAlbumId(albumId);
		List<PhotoVO> photoList = new LinkedList<>();
		photos.forEach((photo)->{
			PhotoVO photoVO = new PhotoVO();
			Utility.copyProperties(photo,photoVO);
			photoList.add(photoVO);
		});
		logger.info("Number of photos in the data source - "+photoList.size());
		return photoList;
	}

	/**
	 * returns a photo
	 */
	@Override
	@Transactional(readOnly=true)
	public PhotoVO getPhoto(String photoId) {
		Photo photo = photoRepository.findOne(photoId);
		PhotoVO photoVO = new PhotoVO();
		Utility.copyProperties(photo,photoVO);
		return photoVO;
	}

	/**
	 * Creates a photo
	 */
	@Override
	@Transactional
	public String createPhoto(PhotoVO photoVO) {
		// Need to create the primary key for this
		String key = keyGenUtil.getNextPhotoKey();
		photoVO.setId(key);
		Photo photo = new Photo();
		Utility.copyProperties(photoVO,photo);
		photo = photoRepository.save(photo);
		return photo.getPhotoId(); // can do the null check here.
	}
	
	/**
	 * Updates a photo
	 */
	@Override
	@Transactional
	public void updatePhoto(String photoId, PhotoVO photoVO) {
		photoVO.setId(photoId);
		Photo photo = photoRepository.findOne(photoId);
		Utility.copyProperties(photoVO,photo);
		photoRepository.save(photo);
	}

	/**
	 * Deletes a photo
	 */
	@Override
	@Transactional
	public void deletePhoto(String photoId) {
		photoRepository.delete(photoId);
	}
}
