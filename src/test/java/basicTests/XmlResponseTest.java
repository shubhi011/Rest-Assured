package basicTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import resources.*;



//This test is to add a place using post,grab the place id, and then delete that place .
//we need to pass the place id from post response into the delete request body.
public class XmlResponseTest {
	
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream("C://Users//ssrivastava6937//eclipse-workspace//restassured//src//main//resources//config//env.properties");
		prop.load(fis);
	}
@Test
 public void delete() throws IOException
 {
	
	
	RestAssured.baseURI=prop.getProperty("HOST");
	//Extract response in res variable using extract() method..statusCode(200).and().contentType(ContentType.JSON).and().
	Response res =(Response) given().	
	queryParam("key",prop.getProperty("KEY")).
	body(Payload.GenerateStringFromResource("C:\\Users\\ssrivastava6937\\eclipse-workspace\\restassured\\src\\main\\resources\\xmlPayload.xml")).
	when().
	post(resources.getResources.getPostResource()).
	then().assertThat().statusCode(200).extract().response(); //response received in raw format -raw data
	
	// Grab the response
	String responseString=res.asString();      //Convert raw data to string 
	System.out.println(responseString);
	
	//Grab the place id from response
	
     XmlPath js=new XmlPath(responseString);//Convert string to json
     String placeId=js.get("place_id"); //placeadd.place_id
     System.out.println(placeId);
	       
	//hit delete request with this place id
     
	given().queryParam("key", "qaclick123").
	body("{"+
			  "\"place_id\": \""+placeId+"\""+
			"}").
	when().post("/maps/api/place/delete/json").
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK"));
	
	    
	     
	     
	    
 }
 
 

}
