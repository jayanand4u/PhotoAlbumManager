package com.teradata.exercise.db.repository;

import com.teradata.exercise.db.entity.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo,String>{

	Iterable<Photo> findByAlbumId(String albumId);
	
}
