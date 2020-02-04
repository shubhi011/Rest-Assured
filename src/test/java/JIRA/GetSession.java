package JIRA;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetSession {

	public static String createSession()
	{
		//create a session
		 RestAssured.baseURI="http://localhost:8090";
		 
		Response res= given()
		   .header("Content-Type", "application/json")
		   .body("{\"username\": \"ssrivastava6937\", \"password\": \"password\" }")
		   .when().post("/rest/auth/1/session")
		    .then().assertThat().statusCode(200).and().extract().response();
		
		         String responseString=res.asString();
		         System.out.println(responseString);
		         
		      JsonPath js=new JsonPath(responseString);
		      String sessionName=js.get("session.name");
		      String sessionId=js.get("session.value");
		      
		      System.out.println("Session is:"+sessionName+":"+sessionId);	
		      
		      return sessionId;
		
	}
}
