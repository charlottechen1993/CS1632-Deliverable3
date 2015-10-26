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

public class ImdbTest_MovieInfo {
	private String baseUrl;
	String movie = "The Martian";
	static WebDriver driver = new FirefoxDriver();
	
	// Start at the home page for IMDB for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.imdb.com/");
		baseUrl = "http://www.imdb.com/";
	}
	
	/* Scenario 1:
	 * Given that I am on a certain movie’s page
	 * When I view the title
	 * Then I should see that it contains the movie’s name
	 */
	@Test
	public void testShowsCorrectTitle() {
		driver.get(baseUrl);
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys(movie);
	    driver.findElement(By.cssSelector("div.suggestionlabel")).click();
		
		String title = driver.getTitle();
		assertTrue(title.contains(movie));
	}
	
	/* Scenario 2: 
	 * Given that I search for a certain movie 
	 * When the page on the movie loads
	 * Then I should be able to easily navigate to the movie’s plot, reviews, 
	 * or award sections via quick links
	 */
	@Test
	public void testQuickLink() {
		driver.get(baseUrl);
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys(movie);
	    driver.findElement(By.cssSelector("div.suggestionlabel")).click();
	   
	    try {
	    	driver.findElement(By.linkText("Full Cast and Crew"));
		    driver.findElement(By.linkText("Plot Summary"));
		    driver.findElement(By.linkText("Trivia"));
		    driver.findElement(By.linkText("Parents Guide"));
		    driver.findElement(By.linkText("Quotes"));
		    driver.findElement(By.linkText("User Reviews"));
		    driver.findElement(By.linkText("Awards"));
		    driver.findElement(By.linkText("Release Dates"));
		    driver.findElement(By.linkText("Message Board"));
		    driver.findElement(By.linkText("Company Credits"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/* Scenario 3:
	 * Given that the I am on a certain movie’s page
	 * When I navigate to the Storyline section
	 * Then I should have the option to see both “Plot Summary” and “Plot Synopsis”
	 */
	@Test
	public void testPlotSummary(){
		driver.get(baseUrl + "");
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys(movie);
	    driver.findElement(By.cssSelector("div.suggestionlabel")).click();
	    WebElement storyline = driver.findElement(By.id("titleStoryLine"));
	    
	    try{
	    	storyline.findElement(By.linkText("Plot Summary"));
		    storyline.findElement(By.linkText("Plot Synopsis"));
	    } catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/*Scenario 4:
	 * Given that the I am on a certain movie’s page
	 * When I navigate to “Frequently Asked Questions”
	 * Then I should have the option to see more than the basic information 
	 * displayed on the current page via a “See more” link and also be warned 
	 * of potential spoilers
	 */
	@Test
	public void testSeeMore(){
		driver.get(baseUrl + "");
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys(movie);
	    driver.findElement(By.cssSelector("div.suggestionlabel")).click();
	    WebElement faq = driver.findElement(By.id("titleFAQ"));
	    
	    try{
	    	faq.findElement(By.linkText("See more"));
	    	faq.findElement(By.cssSelector("span.spoilers"));
	    } catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/* Scenario 5:
	 * Given that I search for a certain movie 
	 * When the page on the movie loads
	 * Then I should have the option to quickly navigate to separate pages via 
	 * links to see only photos, videos, and articles related to the movie 
	 */
	@Test
	public void testMedia(){
		driver.get(baseUrl + "");
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys(movie);
	    driver.findElement(By.cssSelector("div.suggestionlabel")).click();
	    WebElement media = driver.findElement(By.id("titleMediaStrip"));
	    
	    try{
	    	media.findElement(By.partialLinkText("photos"));
	    	media.findElement(By.partialLinkText("videos"));
	    	media.findElement(By.partialLinkText("articles"));
	    } catch (NoSuchElementException nseex) {
			fail();
		}
	    
	}
}
