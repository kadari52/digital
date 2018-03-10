

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Web drive Helper class to create a choosen browser from the config properties file. 
 * It can create 3 types of browsers IE, Chrome and Firefox and prepares them ready for test runs
 * 
 * @author Kamalakar
 *
 */
public class WebDriverHelper 
{
	public static WebDriver createDriver(String browser)
	{
		WebDriver webDriver=null;
		
		if(browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Data\\chromedriver.exe");
			webDriver = new InternetExplorerDriver();
		  	webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}else if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Data\\chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}else if(browser.equalsIgnoreCase("FireFox")){
			System.setProperty("webdriver.gecko.driver", "C:\\Data\\geckodriver.exe");
			webDriver = new FirefoxDriver();
		}else
		{
			throw new InvalidParameterException(browser + "is not a valid parameter");
		}
	  	webDriver.manage().window().maximize();
	  	
		return webDriver;
		
	}
	
	public static void quitDriver(WebDriver driver)
	{
		driver.quit();
	}
}
