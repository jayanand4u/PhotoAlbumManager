package com.teradata.exercise.common;

import com.teradata.exercise.db.entity.Album;
import com.teradata.exercise.db.entity.Photo;
import com.teradata.exercise.vo.AlbumVO;
import com.teradata.exercise.vo.PhotoVO;

public class Utility {
	
	public static void copyProperties(AlbumVO albumVo, Album album) {
		album.setAlbumId(albumVo.getAlbumId());
		album.setTitle(albumVo.getTitle());
		album.setUserId(albumVo.getUserId());
	}

	public static void copyProperties(PhotoVO photoVo, Photo photo) {
		photo.setAlbumId(photoVo.getAlbumId());
		photo.setPhotoId(photoVo.getId());
		photo.setTitle(photoVo.getTitle());
		photo.setUrl(photoVo.getUrl());
	}

	public static void copyProperties(Album album, AlbumVO albumVO) {
		albumVO.setAlbumId(album.getAlbumId());
		albumVO.setTitle(album.getTitle());
		albumVO.setUserId(album.getUserId());
		albumVO.setPhotoList(album.getPhotoVOs());
	}

	public static void copyProperties(Photo photo, PhotoVO photoVO) {
		photoVO.setAlbumId(photo.getAlbumId());
		photoVO.setId(photo.getPhotoId());
		photoVO.setTitle(photo.getTitle());
		photoVO.setUrl(photo.getUrl());
	}

}
