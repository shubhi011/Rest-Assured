package datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//This code is to read the fetch the data of a testcase from sheet
public class TestSample {

	public static void main(String[] args) throws IOException {
		
		ReadDataFromExcel data=new ReadDataFromExcel();
		ArrayList<String> al=data.getData("RestAddBook","Sheet1");
		for(String s :al)
		{
			System.out.println(s);
		}
		
		//Selenium-enter data on UI
		//driver.findElemnt(By.xpath(xpathExpression)).sendkeys(al.get(0));
		
		
		//RestAssured
		HashMap<String,Object> hashmap=new HashMap<>();
		hashmap.put("name", al.get(1));
		hashmap.put("isbn", al.get(2));
		hashmap.put("aisle", al.get(3));
		hashmap.put("author", al.get(4));
		
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
	

}
