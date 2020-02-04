package resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public  class Payload {
	
	public static String getPostData()

	{
		String requestBody ="{"+
				"\"location\": {"+
				"\"lat\": -33.8669710,"+
				"\"lng\": 151.1958750"+
				"},"+
				"\"accuracy\": 50,"+
				"\"name\": \"Google Shoes!\","+
				"\"phone_number\": \"(02) 9374 4000\","+
				"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""+
				"}";
		
		return requestBody;
	}
	
	public static String GenerateStringFromResource(String path)throws IOException
	{
		//This method takes the path of the file .Read the file as bytes and convert the byte to string 
		return new String (Files.readAllBytes(Paths.get(path)));
	}
	
	public static String addBookData(String isbn,String aisle)
	{
		//use freeformatter.com to convert to string
		String payload="{\\r\\n\\r\\n\\\"name\\\":\\\"Learn Appium Automation with Java\\\",\\r\\n\\\"isbn\\\":\\\""+isbn+"\\\",\\r\\n\\\"aisle\\\":\\\""+aisle+"\\\",\\r\\n\\\"author\\\":\\\"John foe\\\"\\r\\n}";
		return payload;
		
	}
}
