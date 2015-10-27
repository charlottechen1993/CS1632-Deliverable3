import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/* As a logged in user
 * I want to be able rate movies
 * So that I can contribute to the IMDB community
 */
public class ImdbTest_Rating {
	private String baseUrl;
	  
	static WebDriver driver = new FirefoxDriver();
	
	// Start at the home page for IMDB for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.imdb.com");
		baseUrl = "http://www.imdb.com/";
	}
}
