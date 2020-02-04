package serializationUsingGoogleMaps;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SerializationTest {

	public static void main(String[] args) {
		RestAssured.baseURI="http://216.10.245.166";	
		//RestAssured.baseURI = "http://rahulshettyacademy.com";
		
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
		
		Response res = given().log().all()
				 .queryParam("key", "qaclick123").body(ap).when().post("/maps/api/place/add/json").then()
				.assertThat().statusCode(200).extract().response();

		String responseString = res.asString();
		System.out.println("Response:"+responseString);
	}

}
