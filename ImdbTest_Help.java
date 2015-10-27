import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/* 
As a general user
I want to be able access a help section
So that I can find answers to questions I have about the site

 */
public class ImdbTest_Help {
	private String baseUrl;
	  
	static WebDriver driver = new FirefoxDriver();
	
	// Start at the home page for IMDB for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.imdb.com");
		baseUrl = "http://www.imdb.com/";
	}
	
	
	// 	Given that I am on the homepage
	//	When I view the page
	//	Then I should find a link “Help” that will lead me to the help section
	@Test
	public void findHelpLink(){
		driver.get(baseUrl);
		try{
			driver.findElement(By.linkText("Help"));
		}catch(Exception x){
			fail();
		}
	}
	
	
	//	Given that I click on “Help” and is linked to the help page
	//	When I view the page
	//	Then I should find sections titled “Top Frequently Asked Questions” and “Help Sections/FAQs”
	@Test
	public void findSections(){
		driver.get(baseUrl);
		driver.findElement(By.linkText("Help")).click();
		String search = driver.findElement(By.id("help_center")).getText() +
						driver.findElement(By.id("help_rhs")).getText();
		
		boolean str1 = search.contains("Top Frequently Asked Questions");
		boolean str2 = search.contains("Help Sections/FAQs");
	
		assertTrue(str1 && str2);
	}
	
	
	// Given that I am on the help page
	// When I view the page
	// Then I should be able to find a “Search Help” input field where I can search questions by inputting keywords 
	@Test
	public void helpInputField(){
		driver.get(baseUrl);
		driver.findElement(By.linkText("Help")).click();
		
		try{
			driver.findElement(By.id("help_search_input"));
		}catch(Exception x){
			fail();
		}
	}
	
	// Given that I perform a search via the “Search Help” bar and there are results that matches my search
	// When I view the result page
	// Then I should be able to see a “Score” next to each question link that suggests me the helpfulness of each results
	@Test
	public void seeScores(){
		driver.get(baseUrl);
	    driver.findElement(By.linkText("Help")).click();
	    driver.findElement(By.id("help_search_input")).clear();
	    driver.findElement(By.id("help_search_input")).sendKeys("credits");
	    driver.findElement(By.cssSelector("input.primary.btn")).click();
	    String search = driver.findElement(By.id("pagecontent")).getText();
	    
	    assertTrue(search.contains("(Score:"));
	}


	// Given that I perform a search via the “Search Help” bar and the results that matches my search exceeds 5
	// When the page loads the top 5 questions that are related to my search
	// Then the page should also display a “Show all results” button that allows me the option to see all results
	@Test
	public void showAllResults(){
		driver.get(baseUrl);
	    driver.findElement(By.linkText("Help")).click();
	    driver.findElement(By.id("help_search_input")).clear();
	    driver.findElement(By.id("help_search_input")).sendKeys("credits");
	    driver.findElement(By.cssSelector("input.primary.btn")).click();
	    
	    try{
	    	driver.findElement(By.xpath("//input[@value='Show all results']"));
	    }catch(Exception X){
	    	fail();
	    }
	    
	}
}
