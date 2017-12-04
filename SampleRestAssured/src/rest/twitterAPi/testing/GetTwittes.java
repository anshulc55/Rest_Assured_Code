package rest.twitterAPi.testing;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetTwittes {
	
	
	String consumerKey = "PBfewhhp10o3ITJLaIRy1nLMg";
	String consumnerSecret = "DrRGwimnjLJ96UbYBYRuQ3LvLkWRnyvQsRNr5b7CGt4jPc62t2";
	String accessToken ="3945395116-1R3LCXsNr9XU6M7cmAwYBtiNSFFP5Ad4tSoCyjB";
	String accessTSecret = "Ki1AFBTDP8kSHmDKPBWur1Jz3G04Y36hxEOndwhTa45Lr";

	@Test
	public void getFirstThreeTwitts() throws IOException{
		
		
		RestAssured.baseURI = "https://api.twitter.com";
		Response  res = given().
				auth().
				oauth(consumerKey, consumnerSecret, accessToken, accessTSecret).
				param("count", 3).log().all().
				
				when().
				get("/1.1/statuses/user_timeline.json").
				
				then().assertThat().statusCode(200).log().all().
		
				extract().response();
	}
}
