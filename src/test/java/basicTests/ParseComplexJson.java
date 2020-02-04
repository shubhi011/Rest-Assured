package basicTests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParseComplexJson {

	@Test
	public void parseJson(){
		
		RestAssured.baseURI="https://maps.googleapis.com";
		//use log().all() with given for logging requests.
		//use log().all() with then for logging response
		Response res=
		 given().
		 param("location","-33.8670522,151.1957362").
	     param("radius","500").
	     param("key","AIzaSyAPq8P6-WQTcB4wXhGZ1IcCRlZm9U5eICA").log().all().
	     when().
	     get("/maps/api/place/nearbysearch/json").
	     then().
	     assertThat().statusCode(200).and().contentType(ContentType.JSON).log().all().
	     and().extract().response();
		
		  String responseString=res.asString();
		  System.out.println(responseString);
		  
		  //print all the names in the response
		  
		  JsonPath js=new JsonPath(responseString);
		  int size=js.get("results.size");  //size of results array in response
		  System.out.println(size);
		 
		for(int i=0;i<size;i++)
		{	
			String names=js.get("results["+i+"].name");
			System.out.println(names);
		  
		}
		  
		  
		  
	}

}
