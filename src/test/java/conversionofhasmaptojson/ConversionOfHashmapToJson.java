package conversionofhasmaptojson;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Payload;

public class ConversionOfHashmapToJson {

	public static void main(String[] args) {
		
		HashMap<String,Object> hashmap=new HashMap<>();
		hashmap.put("name", "Java");
		hashmap.put("isbn", "bcd");
		hashmap.put("aisle", "121");
		hashmap.put("author", "Johnfoe");
		
        RestAssured.baseURI = "http://216.10.245.166";
		
		Response res=given().
				body(hashmap).   
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).and().extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);
			
		JsonPath js =new JsonPath(responseString);
		String ID=js.get("ID");
		System.out.println(ID);
	}

	
	/*if payload is as below
	{

	    "location":{

	        "lat" : -38.383494,

	        "lng" : 33.427362

	    },
	    
	    *create nested hashmap
	    *HashMap<String,Object> map2=new HashMap<>();
	    *map2.put("lat","123");
	    *map2.put("lang","345");
	    *
	    *hashmap.put("location",map2);
	    *
	    *
	    *
	    */

}
