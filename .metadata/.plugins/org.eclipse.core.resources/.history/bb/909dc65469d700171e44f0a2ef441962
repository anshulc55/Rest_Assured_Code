package rest.basic.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseValidation {
	
	@Test
	public void verifyResponse(){
		
		String requestBody = "{"+
				"\"location\": { "+
				"\"lat\": -33.866971123445,"+
				"\"lng\": 151.1958750"+
			"},"+
				"\"accuracy\": 50,"+
				"\"name\": \"Google Shoes!\","+
				"\"phone_number\": \"(02) 9374 4000\","+
				"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""+
			"}";
		
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response  res = given().
			queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
			body(requestBody).
			when().
				post("/maps/api/place/add/json").
		 then().assertThat().statusCode(200).
		
		extract().response();
		
		String respose = res.asString();
		System.out.println(respose);
		
		JsonPath jsonResponse = new JsonPath(respose);
		String placeId = jsonResponse.get("place_id");
		System.out.println(placeId);
		
		
		given().
			queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
			body("{"+
			  "\"place_id\": \""+placeId+"\"" +
		"}").
			when().
			post("/maps/api/place/delete/json").
			
			then().assertThat().statusCode(200).and().body("status", equalTo("OK"));
		

	}
}
