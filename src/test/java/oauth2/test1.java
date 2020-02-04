package oauth2;

import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

public class test1 {
	
	@Test
	public void testOauh(){
		

		/* System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		//driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		
		driver.findElement(By.cssSelector("[type='email']")).sendKeys("dfsf");
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("vxcvd");
		driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		//String url=driver.getCurrentUrl();
		
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAE2L0ypIbIeX1wC6cyjPTnqRmYghWc0WfRSxwGfGVIrx2P4RUhyDzushw_lcQBjx_NZtPqSOv5IudVGgbf5VhE&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=f1af2b96ff95a005e8a1f99633b073a224db47b9..3502&prompt=consent#";
		System.out.println(url);
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		
		System.out.println(code);*/
		
	
		
		String response =
                given().urlEncodingEnabled(false)
                  .queryParams("code","4%2FvAE2L0ypIbIeX1wC6cyjPTnqRmYghWc0WfRSxwGfGVIrx2P4RUhyDzushw_lcQBjx_NZtPqSOv5IudVGgbf5VhE")
                
                   .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .queryParams("grant_type", "authorization_code")
                        .queryParams("state", "verifyfjdss")
                        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                      .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                        
                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                        .when().log().all()
                        .post("https://www.googleapis.com/oauth2/v4/token").asString();
	//	System.out.println(response);
		 JsonPath jsonPath = new JsonPath(response);
		    String accessToken = jsonPath.getString("access_token");
		    System.out.println(accessToken);
		String r2=    given().contentType("application/json").
				 queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
				 .when()
           .get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		System.out.println(r2);

	}


}
