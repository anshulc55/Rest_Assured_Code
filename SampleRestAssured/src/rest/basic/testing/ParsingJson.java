package rest.basic.testing;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParsingJson {
	
	@Test
	public void verifyResponse(){
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response res = given().
		 param("location", "-33.8670522,151.1957362").
		 param("radius", "500").
		 param("type", "cruise").
		 param("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
		 log().all().
		 
		 when().
		 get("/maps/api/place/nearbysearch/json").
		 
		 then().assertThat().statusCode(200).and().
		 contentType(ContentType.JSON).
		 log().all().
		 
		 extract().response();
		
		String response = res.asString();
		
		JsonPath jsonRes = new JsonPath(response);
		
		int arrSize = jsonRes.getInt("results.size()");
		
		for (int i = 0; i < arrSize; i++) {
			String name = jsonRes.getString("results["+i+"].name");
			System.out.println(name);
		}	 
	}
}
