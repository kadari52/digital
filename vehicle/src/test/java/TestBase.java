

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;


/**
 * This is the base class for all tests
 * 
 * @author Kamalakar
 *
 */
public class TestBase 
{
	protected WebDriver webDriver;
	protected HomePage homePage;
	protected Properties testConfig;
	
	/**
	 * This method creates the webdriver and return Home Page
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Before
	public void beforeMethod() throws FileNotFoundException, IOException
	{
		testConfig = new Properties();
		testConfig.load(new FileInputStream("config.properties"));

		 webDriver = WebDriverHelper.createDriver(testConfig.getProperty("browser"));
		 System.out.println(testConfig.getProperty("baseUrl"));
		 webDriver.navigate().to(testConfig.getProperty("baseUrl"));

	  	homePage = new HomePage(webDriver);
		
	}
	

	
	/**
	 * This method is called after the test is done. We close all the windows opened by the test
	 */
	@After
	public void afterMethod()
	{

		WebDriverHelper.quitDriver(webDriver);
	}
	

	
	

}
