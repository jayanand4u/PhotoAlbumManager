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
import com.teradata.exercise.db.entity.Album;
import com.teradata.exercise.db.repository.AlbumRepository;
import com.teradata.exercise.exception.AlbumNotFoundException;
import com.teradata.exercise.vo.AlbumVO;

@Service
public class AlbumServiceImpl implements AlbumService{
	private final Logger logger = LoggerFactory.getLogger(AlbumServiceImpl.class);
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	KeyGenUtil keyGenUtil;
	
	/**
	 * Returns all albums
	 */
	@Override
	@Transactional(readOnly=true)
	public List<AlbumVO> getAllAlbums() {
		Iterable<Album> albumIter = albumRepository.findAll();
		List<AlbumVO> albumList = new LinkedList<>();
		albumIter.forEach((album)-> {
			AlbumVO albumVO = new AlbumVO();
			Utility.copyProperties(album, albumVO);
			albumList.add(albumVO);
		});
		logger.info("No of albums in the data source - "+albumList.size());
		return albumList;
	}

	/**
	 * returns one photo
	 */
	@Override
	@Transactional(readOnly=true)
	public AlbumVO getAlbum(String id) throws AlbumNotFoundException {
		Album album = albumRepository.findOne(id);
		if(album == null){
			throw new AlbumNotFoundException("No album");
		}
		AlbumVO albumVO = new AlbumVO();
		Utility.copyProperties(album, albumVO);
		return albumVO;
	}
	
	/**
	 * Creates a photo
	 */
	@Override
	@Transactional
	public String createAlbum(AlbumVO albumVO) {
		// Need to create the primary key for this album
		String key = keyGenUtil.getNextAlbumKey();
		albumVO.setAlbumId(key);
		Album album = new Album();
		Utility.copyProperties(albumVO,album);
		album = albumRepository.save(album);
		return album.getAlbumId(); // Can do null check here.
	}

	/**
	 * Updates a photo
	 */
	@Override
	@Transactional
	public void updateAlbum(String albumId, AlbumVO albumVO) {
		albumVO.setAlbumId(albumId);
		Album album = albumRepository.findOne(albumVO.getAlbumId());
		Utility.copyProperties(albumVO, album);
		albumRepository.save(album);
	}
	

	/**
	 * Deletes a photo
	 */
	@Override
	@Transactional
	public void deleteAlbum(String id) {
		albumRepository.delete(id);
	}
}
