package basicTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;



public class GetTest {
	
@Test
 public void test1()
 {
	 
	
	RestAssured.baseURI="https://maps.googleapis.com";
	
	given().
	       param("location","-33.8670522,151.1957362").
	       param("radius","500").
	       param("key","AIzaSyAPq8P6-WQTcB4wXhGZ1IcCRlZm9U5eICA").
	       when().
	       get("/maps/api/place/nearbysearch/json").
	       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	       body("results[0].name",equalTo("Sydney")).and().
	       body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	       header("Server","scaffolding on HTTPServer2");
	       
	//Parse the json -fetch all the 20 places returned in response
	   
	       
	      
	
	     
	     
	     
	    
 }
 
 

}
