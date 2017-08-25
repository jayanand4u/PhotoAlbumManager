package com.teradata.exercise.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teradata.exercise.db.dao.KeyGenDAO;

@Component
public class KeyGenUtil {
	private final Logger logger = LoggerFactory.getLogger(KeyGenUtil.class);
	
	@Autowired
	KeyGenDAO keyGenDAO;
	
	public String getNextAlbumKey() {
		String nextVal = keyGenDAO.getNextSeqNumber("album.ALBUM_SEQUENCE");
		logger.info("New album seq value - "+nextVal);
		return "PAMA"+nextVal;
	}

	public String getNextPhotoKey() {
		String nextVal = keyGenDAO.getNextSeqNumber("album.PHOTO_SEQUENCE");
		logger.info("New photo seq value - "+nextVal);
		return "PAMP"+nextVal;
	}
}
