import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
 * As a user (logged in or not logged in)
 * I want the site to show me new movies with comprehensive information 
 * in terms of rating, genre, theater location, description, etc. 
 * So that I am able to get most updated information on new movies
 */

public class ImdbTest_NewMovies {
	private String baseUrl;
	  
	static WebDriver driver = new FirefoxDriver();
	
	// Start at the home page for IMDB for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.imdb.com/");
		baseUrl = "http://www.imdb.com/";
	}
	
	/* Given that I am on the “Showtimes & Tickets” page
	 * When I navigate through the page
	 * Then I should be able to see lists of movies being categorized to “In 
	 * Theaters” and "Coming Soon"
	 */
	@Test
	public void testShowCategory() {
		driver.get(baseUrl);
	    driver.findElement(By.linkText("In Theaters")).click();
	   
	    try {
	    	driver.findElement(By.linkText("In Theaters"));
		    driver.findElement(By.linkText("Coming Soon"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
}
