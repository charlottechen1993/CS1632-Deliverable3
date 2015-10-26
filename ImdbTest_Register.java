import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As someone who is not already a user
 * I want to be able register for IMDB
 * So that I can have access to more functionalities on the site
 */

public class ImdbTest_Register {
	private String baseUrl;
	  
	static WebDriver driver = new FirefoxDriver();
	
	// Start at the home page for IMDB for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.imdb.com");
		baseUrl = "http://www.imdb.com/";
	}
	
	//	Scenario 1:
	//	Given I enter an email that has already been registered with
	//	When I try to register with that email
	//	Then site will assume that I forgot my password and give me the 
	//	opportunity to reset my password
	@Test
	public void testRegisteredEmail(){
		driver.get(baseUrl);
	    driver.findElement(By.linkText("Register")).click();
	    driver.findElement(By.id("first_name")).sendKeys("Charlotte");
	    driver.findElement(By.id("last_name")).sendKeys("Chen");
	    driver.findElement(By.id("gender_f")).click();
	    driver.findElement(By.id("year")).sendKeys("1993");
	    driver.findElement(By.id("postal")).sendKeys("15213");
	    driver.findElement(By.id("email")).sendKeys("xueli_chen@yahoo.com");
	    driver.findElement(By.id("password1")).sendKeys("naruto123");
	    driver.findElement(By.id("password2")).sendKeys("naruto123");
	    driver.findElement(By.xpath("//input[@value='Register']")).click();
	    
	    WebElement resetPw = driver.findElement(By.linkText("Reset now"));
		assertTrue(resetPw.isDisplayed());
	}
}