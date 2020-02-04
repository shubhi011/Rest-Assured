package basicTests.LibraryAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Payload;

import static io.restassured.RestAssured.given;


public class AddBook {
  
	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res=given().
				body(Payload.addBookData("adsfs","6464")).   //Control test data through test case 
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).and().extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);
			
		JsonPath js =new JsonPath(responseString);
		String ID=js.get("ID");
		System.out.println(ID);
		
	}
	
	
	
	
}
