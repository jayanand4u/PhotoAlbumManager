package com.teradata.exercise.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teradata.exercise.common.APISuccessResponse;
import com.teradata.exercise.service.PhotoService;
import com.teradata.exercise.vo.PhotoVO;

/**
 * Exposes the endpoints to perform CRUD operations on the photos.
 * @author J
 *
 */
@RestController
public class PhotoController {
	Logger logger = LoggerFactory.getLogger(PhotoController.class);
	
	@Autowired
	PhotoService photoService;
	
	/**
	 * Retrieves and returns all the photos in an album from the data source.
	 * @param albumId the album id of an album.
	 * @return all photos in the album.
	 */
	@RequestMapping(value="/v1/albums/{id}/photos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PhotoVO>> retrievePhotos(@PathVariable(name="id", required=true) String albumId){
		List<PhotoVO> photos = photoService.getPhotos(albumId);
		return new ResponseEntity<>(photos, HttpStatus.OK);
	}
	
	/**
	 * Returns the photo referenced by the photo id in the request.
	 * @param photoId the photo id.
	 * @return photo
	 */
	@RequestMapping(value="/v1/photos/{photoId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PhotoVO> retrievePhoto(@PathVariable(name="photoId", required=true) String photoId){
		PhotoVO photoVO = photoService.getPhoto(photoId);
		return new ResponseEntity<>(photoVO, HttpStatus.OK);
	}
	
	/**
	 * Validates the photo attributes in the request. If validation is successful, then creates the new photo
	 * @param photoVO holds attributes of the photo.
	 * @return photo id of the newly created photo along with the success message.
	 */
	@RequestMapping(value="/v1/photos",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APISuccessResponse> createPhoto(@RequestBody(required = true) @Valid PhotoVO photoVO){
		String photoId = photoService.createPhoto(photoVO);
		return new ResponseEntity<>(new APISuccessResponse("Photo successfully created",photoId), HttpStatus.OK);
	}
	
	/**
	 * Validates the album attributes in the request. If validation is successful then Updates the photo referenced by the photo id in the request.
	 * @param photoId the photo id
	 * @param photoVO holds new attribute values of the photo. 
	 * @return the photo id of the updated photo along with the success message.
	 */
	@RequestMapping(value="/v1/photos/{photoId}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APISuccessResponse> updatePhoto(@PathVariable(name="photoId", required=true) String photoId, @RequestBody(required = true) @Valid PhotoVO photoVO){
		photoService.updatePhoto(photoId,photoVO);
		return new ResponseEntity<>(new APISuccessResponse("Photo successfully updated",photoId), HttpStatus.OK);
	}
	
	/**
	 * Delete the photo referenced by the photo id in the request.
	 * @param photoId the id of the photo.
	 * @return the http status code.
	 */
	@RequestMapping(value="/v1/photos/{photoId}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePhoto(@PathVariable(name="photoId", required=true) String photoId){
		photoService.deletePhoto(photoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	
	
}
