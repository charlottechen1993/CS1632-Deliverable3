import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/* As someone who is not already a user
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
	

	/* Scenario 1:
	 * Given I enter an email that has already been registered with
	 * When I try to register with this email
	 * Then “Email already associated with an account. Did you forget 
	 * your password?” should print next to the email input
	 */
	@Test
	public void testRegisteredEmailMsg(){
		driver.get(baseUrl + "/register-imdb/form-v2?");
	    driver.findElement(By.id("first_name")).sendKeys("Daniel");
	    driver.findElement(By.id("last_name")).clear();
	    driver.findElement(By.id("last_name")).sendKeys("Hui");
	    driver.findElement(By.id("gender_m")).click();
	    driver.findElement(By.id("year")).clear();
	    driver.findElement(By.id("year")).sendKeys("1993");
	    driver.findElement(By.id("postal")).clear();
	    driver.findElement(By.id("postal")).sendKeys("18966");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("danielhui123@gmail.com");
	    driver.findElement(By.id("password1")).clear();
	    driver.findElement(By.id("password1")).sendKeys("@zxcvbnm3");
	    driver.findElement(By.id("password2")).clear();
	    driver.findElement(By.id("password2")).sendKeys("@zxcvbnm3");
	    driver.findElement(By.xpath("//input[@value='Register']")).click();
	    String displayedSearch = driver.findElement(By.className("reg_form")).getText();
	    
	    assertTrue(displayedSearch.contains("Email already associated with an account. Did you forget your password?"));
	}
	
	/* Scenario 2:
	 * Given I enter an email that has already been registered with
	 * When I try to register with that email
	 * Then site will assume that I forgot my password and give me the 
	 * opportunity to reset my password
	 */
	@Test
	public void testRegisteredEmailReset(){
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
	
	/* Scenario 3: 
	 * Given I enter mismatching passwords
	 * When I try to register with these passwords
	 * Then “Passwords must match” should print next to the password input 
	 */
	@Test
	public void testMismatchingPassword(){
		driver.get(baseUrl + "/register-imdb/form-v2?");
	    driver.findElement(By.id("first_name")).clear();
	    driver.findElement(By.id("first_name")).sendKeys("Daniel");
	    driver.findElement(By.id("last_name")).clear();
	    driver.findElement(By.id("last_name")).sendKeys("Hui");
	    driver.findElement(By.id("gender_m")).click();
	    driver.findElement(By.id("year")).clear();
	    driver.findElement(By.id("year")).sendKeys("1993");
	    driver.findElement(By.id("postal")).clear();
	    driver.findElement(By.id("postal")).sendKeys("18966");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("danielhui123@gmail.com");
	    driver.findElement(By.id("password1")).clear();
	    driver.findElement(By.id("password1")).sendKeys("@zxcvbnm3");
	    driver.findElement(By.id("password2")).clear();
	    driver.findElement(By.id("password2")).sendKeys("@zxcvbnm4");
	    driver.findElement(By.xpath("//input[@value='Register']")).click();
	    
	    String displayedSearch = driver.findElement(By.className("reg_form")).getText();
	    assertTrue(displayedSearch.contains("Passwords must match"));
	}
	
	/* Scenario 4:
	 * Given I enter a password that does not include a symbol or a number
	 * When I try to register with this password
	 * Then “Password must contain numbers or symbols” should print next to 
	 * the password input
	 */
	@Test
	public void testPwWithoutSymbolorNumber(){
		driver.get(baseUrl + "/register-imdb/form-v2?");
	    driver.findElement(By.id("first_name")).clear();
	    driver.findElement(By.id("first_name")).sendKeys("Daniel");
	    driver.findElement(By.id("last_name")).clear();
	    driver.findElement(By.id("last_name")).sendKeys("Hui");
	    driver.findElement(By.id("gender_m")).click();
	    driver.findElement(By.id("year")).clear();
	    driver.findElement(By.id("year")).sendKeys("1993");
	    driver.findElement(By.id("postal")).clear();
	    driver.findElement(By.id("postal")).sendKeys("18966");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("danielhui123");
	    driver.findElement(By.id("password1")).clear();
	    driver.findElement(By.id("password1")).sendKeys("zxddcvbnm");
	    driver.findElement(By.id("password2")).clear();
	    driver.findElement(By.id("password2")).sendKeys("zxddcvbnm");
	    driver.findElement(By.xpath("//input[@value='Register']")).click();
	    
	    String displayedSearch = driver.findElement(By.className("reg_form")).getText();
	    assertTrue(displayedSearch.contains("Password must contain numbers or symbols"));
	}
	
	/* Scenario 5:
	 * Given I enter an email without a domain(gmail.com,hotmail.com,pitt.edu)
	 * When I try to register with this email
	 * Then “Please supply a valid email” should print next to the email input
	 */
	@Test
	public void testEmailWithoutDomain(){
		driver.get(baseUrl + "/register-imdb/form-v2?");
	    driver.findElement(By.id("first_name")).clear();
	    driver.findElement(By.id("first_name")).sendKeys("Daniel");
	    driver.findElement(By.id("last_name")).clear();
	    driver.findElement(By.id("last_name")).sendKeys("Hui");
	    driver.findElement(By.id("gender_m")).click();
	    driver.findElement(By.id("year")).clear();
	    driver.findElement(By.id("year")).sendKeys("1993");
	    driver.findElement(By.id("postal")).clear();
	    driver.findElement(By.id("postal")).sendKeys("18966");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("danielhui123");
	    driver.findElement(By.id("password1")).clear();
	    driver.findElement(By.id("password1")).sendKeys("@zxcvbnm3");
	    driver.findElement(By.id("password2")).clear();
	    driver.findElement(By.id("password2")).sendKeys("@zxcvbnm3");
	    driver.findElement(By.xpath("//input[@value='Register']")).click();
	    
	    String displayedSearch = driver.findElement(By.className("reg_form")).getText();
	    assertTrue(displayedSearch.contains("Please supply a valid email"));
	}
	
	/* Scenario 6:
	 * Given I enter a password less than 8 characters
	 * When I try to register with this password
	 * Then ”Password must be at least 8 characters” should print next to the 
	 * password input
	 */
	@Test
	public void testPwTooShort(){
		driver.get(baseUrl + "/register-imdb/form-v2?");
	    driver.findElement(By.id("first_name")).clear();
	    driver.findElement(By.id("first_name")).sendKeys("Daniel");
	    driver.findElement(By.id("last_name")).clear();
	    driver.findElement(By.id("last_name")).sendKeys("Hui");
	    driver.findElement(By.id("gender_m")).click();
	    driver.findElement(By.id("year")).clear();
	    driver.findElement(By.id("year")).sendKeys("1993");
	    driver.findElement(By.id("postal")).clear();
	    driver.findElement(By.id("postal")).sendKeys("18966");
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("danielhui123@gmail.com");
	    driver.findElement(By.id("password1")).clear();
	    driver.findElement(By.id("password1")).sendKeys("@vbnm3");
	    driver.findElement(By.id("password2")).clear();
	    driver.findElement(By.id("password2")).sendKeys("@vbnm3");
	    driver.findElement(By.xpath("//input[@value='Register']")).click();
	    
	    String displayedSearch = driver.findElement(By.className("reg_form")).getText();
	    assertTrue(displayedSearch.contains("Password must be at least 8 characters"));
	}
}