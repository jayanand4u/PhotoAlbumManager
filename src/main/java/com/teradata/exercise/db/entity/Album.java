package com.teradata.exercise.db.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.teradata.exercise.vo.PhotoVO;

@Entity
@Table(name = "ALBUM") 
public class Album extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	String albumId;
	
	String userId;
	
	String title;

	@OneToMany(mappedBy="albumId")
	List<Photo> photos;
	
	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public List<PhotoVO> getPhotoVOs() {
		List<PhotoVO> photoList = new LinkedList<PhotoVO>();
		photos.forEach((photo)->{
			PhotoVO photoVO = new PhotoVO();
			photoVO.setAlbumId(photo.albumId);
			photoVO.setId(photo.getPhotoId());
			photoVO.setTitle(photo.getTitle());
			photoVO.setUrl(photo.getUrl());
			photoList.add(photoVO);
		});
		return photoList;
		
	}
	
}
