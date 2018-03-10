

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Home Page class. For this test purpose it is assumed that the home page is Get Vehicle information 
 * from DVLA
 * 
 * This class is part of POM and has the methods for the functionality required for this test
 * 
 * @author Kamalakar
 *
 */
public class HomePage extends PageBase 
{
	protected Properties testConfig;
	
	public HomePage(WebDriver driver) throws FileNotFoundException, IOException
	{
		super(driver);
		testConfig = new Properties();
		testConfig.load(new FileInputStream("config.properties"));
	}
	
	/**
	 * This method is for clicking the Start button on the Get Vehicle information from DVLA
	 * 
	 * @return same page
	 */
	public VehicleEnquiryPage startCheck()
	{
		driver.findElement(By.xpath("//*[@id=\"get-started\"]/a")).click();

		return new VehicleEnquiryPage(driver);
		
	}
	

	
}
