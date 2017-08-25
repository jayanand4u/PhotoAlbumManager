package com.teradata.exercise;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.jayway.restassured.http.ContentType;
import com.teradata.exercise.vo.AlbumVO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class PhotoAlbumManagerApplicationTests {
	AlbumVO albumVO;
	
	@LocalServerPort
	private int PORT;
	 
	@Autowired
	RestTemplate restTemplate;
	
	@Before
	public void setup(){
		albumVO = new AlbumVO();
		albumVO.setTitle("My custom album");
		albumVO.setUserId("1");
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testAlbumCreateStatusCode(){
		given().contentType(ContentType.JSON).body(albumVO)
			.when().post(getBaseURL()+"/albums").then().statusCode(200);
		
	}
	
	@Test
	public void testAlbumCreate(){
		given().contentType(ContentType.JSON).body(albumVO)
			.when().post(getBaseURL()+"/albums").then()
			.body("id", notNullValue())
			.body("message", notNullValue());
		
	}
	
 private String getBaseURL() {
		    return String.format("http://localhost:%d/v1", PORT);
		  }
}
