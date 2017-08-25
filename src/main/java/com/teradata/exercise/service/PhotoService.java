package com.teradata.exercise.service;

import java.util.List;

import com.teradata.exercise.vo.PhotoVO;

public interface PhotoService {

	List<PhotoVO> getPhotos(String albumId);

	PhotoVO getPhoto(String photoId);

	void updatePhoto(String photoId, PhotoVO photoVO);

	void deletePhoto(String photoId);

	String createPhoto(PhotoVO photoVO);

}
