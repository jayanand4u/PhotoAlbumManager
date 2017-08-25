package com.teradata.exercise.service;

import java.util.List;

import com.teradata.exercise.exception.AlbumNotFoundException;
import com.teradata.exercise.vo.AlbumVO;

public interface AlbumService {
	List<AlbumVO> getAllAlbums();

	AlbumVO getAlbum(String id) throws AlbumNotFoundException;

	void deleteAlbum(String id);

	void updateAlbum(String albumId, AlbumVO albumVO);

	String createAlbum(AlbumVO albumVO);
}
