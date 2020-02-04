package oauth2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OauthTest {

	@Test
	public void testOauth() throws InterruptedException {
//Gmail automation has been blocked by google
		
		/*System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ssrivastava6937\\eclipse-workspace\\restassured\\src\\main\\resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(
//				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifydss");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srivastava2210shubhi");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String url = driver.getCurrentUrl();
		System.out.println(url);*/
		
		
        //hit the url manually and copy the code.
		//"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifydss")
		//ask dev to increase lifetime scope of url so that it is availble for 2-3 days.
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifydss&code=4%2FvAF277inIWML-vJsD2H31EvixTpN3B2KEjZaavSt-c4FKZakmUw_ZAaamEmn21_AvTrFHUllyB7jl-VHkmyaQKA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=71d7b98e8ef4c2643036c2e218e69498b39dc17a..ebda&prompt=consent#";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
				 .queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.get("access_token");

		String response = given().queryParam("access_token", accessToken).when()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}
}
