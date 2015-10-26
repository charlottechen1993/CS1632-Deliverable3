import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * As a user (registered or unregistered)
 * I want to be able to search/filter contents based on keywords or categories 
 * So that I can quickly find information on the content (movies, shows, theaters, 
 * etc.) I am looking for
 */

public class ImdbTest_Searching {
	private String baseUrl;
	  
	static WebDriver driver = new FirefoxDriver();
	
	// Start at the home page for IMDB for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.imdb.com");
		baseUrl = "http://www.imdb.com/";
	}
	
	//	Scenario 1:
	//	Given that I choose a category in the search bar and don’t enter anything in 
	//	the key word search bar
	//	When I press the search button
	//	Then the website should display “Enter a word or phrase to search on”
	@Test
	public void testEmptySearch () {
		driver.get(baseUrl);
	    driver.findElement(By.id("navbar-submit-button")).click();
	    assertEquals("Enter a word or phrase to search on.", driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[3]/div[1]/div")).getText());
	}
	
	//	Scenario 2:
	//	Given that I enter a key word in the search bar and leave the category filter as 
	//	default “all”
	//	When I press the search button
	//	Then the website should display the message “Displaying [number] results for” 
	//	followed by the exact word/phrase I entered in the search bar 
	@Test
	public void testResultMatchesSearch() {
		driver.get(baseUrl);
	    driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys("hello");
	    driver.findElement(By.id("navbar-submit-button")).click();
	    
	    String search = driver.findElement(By.xpath("//input[@id='navbar-query']")).getAttribute("value");
	    String displayedSearch = driver.findElement(By.className("findSearchTerm")).getText();
	    
	    assertTrue(displayedSearch.contains(search));
	}
	
	//	Scenario 3:
	//	Given that I enter a key word in the search bar and select “title” from the dropdown
	//	When I press the search button and the website displays the list of results 
	//	that contains the word/phrase that I input
	//	Then I should be given the option to further filter the result to match exactly 
	//	to my search by clicking the link “Exact title matches”
	@Test
	public void testExactMatchOption(){
		driver.get(baseUrl);
	    driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys("hello");
	    driver.findElement(By.id("navbar-submit-button")).click();
	    
	    try {
	    	driver.findElement(By.linkText("Exact title matches"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//	Scenario 4: 
	//	Given that I am on the homepage
	//	When I open the dropdown box in the search bar
	//	Then I should be able to see a list of categories I can use to filter my search result
	@Test
	public void testPlaceholder() {
		driver.get(baseUrl);
	    String placeholder = driver.findElement(By.id("navbar-query")).getAttribute("placeholder");
	    assertEquals(placeholder, "Find Movies, TV shows, Celebrities and more...");
	}
	
	//	Scenario 5: 
	//	Given that I enter an invalid input (e.g. symbols, long numbers, or long strings) into 
	//	the search bar that will return no results
	//	When I press the search button
	//	Then the website should display “No results found for ” followed by the input I entered
	@Test
	public void testInvalidInput() {
		String input = "789437547#*@1$#870932";
		driver.get(baseUrl);
	    driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys(input);
	    driver.findElement(By.id("navbar-submit-button")).click();
	    
	    String no_result = driver.findElement(By.className("findHeader")).getText();
	    assertEquals(no_result, "No results found for \""+ input +"\"");
	}
	
	//	Scenario 6:
	//	Given that I am on the homepage
	//	When I select/click “Advanced Search” from the bottom of the dropdown menu
	//	Then I should be redirected to a new page with options of “Advanced Title Search”, 
	//	“Advanced Name Search”, and “Collaborations and Overlaps”
	@Test
	public void testAdvancedSearch() {
		driver.get(baseUrl);
	    new Select(driver.findElement(By.id("quicksearch"))).selectByVisibleText("Advanced Search »");

	    try {
	    	driver.findElement(By.linkText("Advanced Title Search"));
		    driver.findElement(By.linkText("Advanced Name Search"));
		    driver.findElement(By.linkText("Collaborations and Overlaps"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
}
