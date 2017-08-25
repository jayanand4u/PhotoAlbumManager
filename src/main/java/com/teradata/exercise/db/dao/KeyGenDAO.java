package com.teradata.exercise.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KeyGenDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String getNextSeqNumber(String sequenceName){
		String val = jdbcTemplate.queryForObject("call NEXT VALUE FOR "+ sequenceName,null,String.class);
		return val;
	}
}
