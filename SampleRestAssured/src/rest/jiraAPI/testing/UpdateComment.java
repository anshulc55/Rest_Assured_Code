package rest.jiraAPI.testing;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateComment {
	
	@Test
	public void verifyResponse() throws IOException{
		
		String requestBody = generateString("JiraLogin.json");
		
		// Login JIRA
		
		RestAssured.baseURI = "http://localhost:8080";
		Response  res = given().
				contentType(ContentType.JSON).
			body(requestBody).
			when().
				post("/rest/auth/1/session").
		 then().assertThat().statusCode(200).
		
		extract().response();
		
		String respose = res.asString();
		
		JsonPath jsonRes = new JsonPath(respose);
		String sessionID = jsonRes.getString("session.value");
	
		
		
		// Add Comment
		String createCmntBody = generateString("AddCmnt.json");
		Response  addCmntResponse = given().
				contentType(ContentType.JSON).
				header("cookie", "JSESSIONID=" + sessionID+"").
			body(createCmntBody).
			when().
				//post("/rest/api/2/issue/"+issueID+"/comment").
				post("/rest/api/2/issue/RAT-9/comment").
		 then().assertThat().statusCode(201).log().all().
		
		 extract().response();

		JsonPath addCmntResJson = new JsonPath(addCmntResponse.asString());
		String cmntID = addCmntResJson.getString("id");
		
		
		System.out.println("Comment Added");
		
		//Update Comment
		String UpdateCmntBody = generateString("UpdateCmnt.json");
		Response  updateCmntResponse = given().
				contentType(ContentType.JSON).
				header("cookie", "JSESSIONID=" + sessionID+"").
			body(UpdateCmntBody).
			when().
				put("/rest/api/2/issue/RAT-9/comment/" +cmntID+"").
		 then().assertThat().statusCode(200).log().all().
		
		 extract().response();

		/*JsonPath addCmntResJson = new JsonPath(addCmntResponse.asString());
		String cmntID = jsonRes.getString("id");*/
		
		given().
		contentType(ContentType.JSON).
		header("cookie", "JSESSIONID=" + sessionID+"").
		when().
		delete("/rest/api/2/issue/RAT-9/comment/" +cmntID+"").
          then().assertThat().statusCode(204).log().all();
		
	}
	
	
	public static String generateString(String filename) throws IOException{
		String filePath = System.getProperty("user.dir")+"\\Payloads\\"+filename;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

}
