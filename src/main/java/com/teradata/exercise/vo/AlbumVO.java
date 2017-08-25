package com.teradata.exercise.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumVO extends ResourceSupport implements Comparable<AlbumVO>{
	
	@JsonProperty(value="id")
	private String albumId;
	
	@NotBlank(message = "userId can't be null or empty")
	private String userId;
	
	@NotBlank(message = "album title can't be null or empty")
	private String title;
	
	@JsonInclude(Include.NON_NULL)
	List<PhotoVO> photoList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public int compareTo(AlbumVO o) {
		int photoId = Integer.valueOf(albumId);
		int pId = Integer.valueOf(o.albumId);
		return photoId - pId;
	}
	public List<PhotoVO> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<PhotoVO> photoList) {
		this.photoList = photoList;
	}
	
	
	
}
