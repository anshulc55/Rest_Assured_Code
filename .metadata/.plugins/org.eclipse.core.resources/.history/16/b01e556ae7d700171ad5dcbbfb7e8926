package rest.basic.xml;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class PostXMLRequest {
	
	@Test
	public void verifyResponse() throws IOException{
		
		System.out.println(System.getProperty("user.dir"));
		
		String requestBody = generateString("PostXMLPayload.xml");
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response  res = given().
			queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
			body(requestBody).
			when().
				post("/maps/api/place/add/xml").
		 then().assertThat().statusCode(200).
		
		extract().response();
		
		String respose = res.asString();
		System.out.println(respose);
		
		XmlPath xmlResponse = new XmlPath(respose);
		String placeId = xmlResponse.get("PlaceAddResponse.place_id");
		System.out.println("*********************");
		System.out.println(placeId);

	}
	
	public static String generateString(String filename) throws IOException{
		String filePath = System.getProperty("user.dir")+"\\Payloads\\"+filename;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

}
