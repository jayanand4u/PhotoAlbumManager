package com.teradata.exercise.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teradata.exercise.db.entity.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album,String>{

}
