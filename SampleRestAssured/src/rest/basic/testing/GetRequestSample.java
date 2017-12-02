package rest.basic.testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestSample {
	
	 //BaseURI
	public static String baseURI = "https://maps.googleapis.com";
	
	public static void main(String args[]){
		
		RestAssured.baseURI = baseURI;
		given().
		 param("location", "-33.8670522,151.1957362").
		 param("radius", "500").
		 param("type", "restaurant").
		 param("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
		 when().
		 get("/maps/api/place/nearbysearch/json").
		 then().assertThat().statusCode(200).and().
		 contentType(ContentType.JSON).and().
		 body("results[0].name", equalTo("The Little Snail Restaurant") );
		 System.out.println("Request is executed successfully");
	}
	
}
