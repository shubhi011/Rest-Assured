package JIRA;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Payload;

import static io.restassured.RestAssured.given;

import java.io.IOException;



public class AddComment {
	
	
	@Test
	public String addComment() throws IOException
	{
		RestAssured.baseURI="http://localhost:8090";
		 
		Response res= given()
		   .header("Content-Type", "application/json").header("Cookie", "JSESSIONID="+GetSession.createSession())
		   .body("{\"body\" :\"i have added an automated comment\"}")
		   .when().post("/rest/api/2/issue/"+CreateIssue.createIssue()+"/comment")
		    .then().assertThat().statusCode(201).and().extract().response();
		
		         String responseString=res.asString();
		         System.out.println(responseString);
		         
		    JsonPath js=new JsonPath(responseString);
		     String commentId=js.get("id");
		    
		     return commentId;
		     
		     
		      
	}
	
	
}
