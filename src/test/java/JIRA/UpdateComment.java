package JIRA;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Payload;

import static io.restassured.RestAssured.given;

import java.io.IOException;



public class UpdateComment {
	
	
	@Test
	public void updateComment() throws IOException
	{
		RestAssured.baseURI="http://localhost:8090";
		 
		Response res= given()
		   .header("Content-Type", "application/json").header("Cookie", "JSESSIONID="+GetSession.createSession())
		   .body("{\"body\" :\"i have updated an automated comment\"}")
		   .when().put("/rest/api/2/issue/10002/comment/10000")
		    .then().assertThat().statusCode(200).and().extract().response();
		
		         String responseString=res.asString();
		         System.out.println(responseString);
		         
		    
		  		     
		      
	}
	
	
}
