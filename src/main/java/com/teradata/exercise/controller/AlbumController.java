package com.teradata.exercise.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import com.teradata.exercise.exception.AlbumNotFoundException;
import com.teradata.exercise.service.AlbumService;
import com.teradata.exercise.vo.AlbumVO;

/**
 * This is a REST controller that exposes API to perform CRUD operation on Album.
 * @author J
 *
 */
@RestController
@RequestMapping("/v1/albums")
public class AlbumController {
	Logger logger = LoggerFactory.getLogger(AlbumController.class);
	
	@Autowired
	AlbumService albumService;
	
	/**
	 * Retrieves and returns the entire albums in the data source.
	 * @return all albums
	 */
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlbumVO>> retrieveAlbums(){
		List<AlbumVO> albumList = albumService.getAllAlbums();
		albumList.forEach((album)->{
			try {
				album.add(linkTo(methodOn(AlbumController.class).retrieveAlbum(album.getAlbumId())).withSelfRel());
			} catch (Exception e) {
				logger.error("Exception while creating hateaos link",e);
			}
			album.add(linkTo(methodOn(AlbumController.class).deleteAlbum(album.getAlbumId())).withRel("delete"));
		});
		return new ResponseEntity<>(albumList, HttpStatus.OK);
		
	}
	
	/**
	 * retrieves and returns the album from the data source.
	 * @param id an album id.
	 * @return album corresponding to the album id in the request.
	 * @throws AlbumNotFoundException
	 */
	@RequestMapping(value="{id}",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlbumVO> retrieveAlbum(@PathVariable(name="id", required = true) String id) throws AlbumNotFoundException{
		AlbumVO albumVO = albumService.getAlbum(id);
		albumVO.add(linkTo(methodOn(AlbumController.class).retrieveAlbum(id)).withSelfRel());
		return new ResponseEntity<>(albumVO,HttpStatus.OK);
	}
	
	/**
	 * Validates the album attributes in the request. If validation is successful, then creates new album.
	 * @param albumVO holds the album attributes.
	 * @return the album id of the newly created album along with the success message.
	 */
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APISuccessResponse> createAlbum(@RequestBody(required = true) @Valid AlbumVO albumVO){
		String albumId = albumService.createAlbum(albumVO);
		return new ResponseEntity<>(new APISuccessResponse("Album successfully created",albumId),HttpStatus.OK);
	}
	
	/**
	 * Validates the album attributes in the request. If validation is successful then Updates the album referenced by the album id in the request.
	 * @param albumId the album id.
	 * @param albumVO the new attribute values of the album.
	 * @return the album id of the updated album along with the success message.
	 */
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	public ResponseEntity<APISuccessResponse> updateAlbum(@PathVariable(name="id", required=true) String albumId, @RequestBody(required = true) @Valid AlbumVO albumVO){
		albumService.updateAlbum(albumId, albumVO);
		return new ResponseEntity<>(new APISuccessResponse("Album successfully updated",albumId),HttpStatus.OK);
	}
	
	/**
	 * Delete the album referenced by the album id in the request.
	 * @param id the album id.
	 * @return the http status code.
	 */
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAlbum(@PathVariable(name="id", required = true) String id){
		albumService.deleteAlbum(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
