package com.teradata.exercise.vo;

import org.hibernate.validator.constraints.NotBlank;

public class PhotoVO {
	@NotBlank(message = "albumId can't be null or empty")
	private String albumId;
	
	private String id;
	
	@NotBlank(message = "Photo title can't be null or empty")
	private String title;
	
	@NotBlank(message = "Photo url can't be null or empty")
	private String url;
	
	
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
