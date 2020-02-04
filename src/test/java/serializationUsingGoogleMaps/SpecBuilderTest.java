package serializationUsingGoogleMaps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;


public class SpecBuilderTest {

	public static void main(String[] args) {
		
		//Request Specification
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://216.10.245.166").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
	    //Response Specification
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
				
		AddPlacePojo ap=new AddPlacePojo();
		ap.setAccuracy(50);
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");
		List<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		ap.setTypes(types);
		
		LocationPojo l=new LocationPojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);		
		ap.setLocation(l);
		
		RequestSpecification request = given().log().all().spec(reqSpec);			
				
		Response res =	request.body(ap).when().post("/maps/api/place/add/json").then()
				.spec(resSpec).extract().response();

		String responseString = res.asString();
		System.out.println("Response:"+responseString);
		
		
		 
	}

}
