

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This is the page class for Vehicle Enquiry. The Registration number is keyed and submitted on 
 * this page
 * 
 * @author Kamalakar
 *
 */
public class VehicleEnquiryPage extends PageBase
{

	public VehicleEnquiryPage(WebDriver driver) {
		super(driver);

	}
	
	public VehicleEnquiryResultsPage keyInVehicleReg(String regno)
	{
		driver.findElement(By.id("Vrm")).sendKeys(regno);
		driver.findElement(By.name("Continue")).click();
		
		return new VehicleEnquiryResultsPage(driver);
	}
	

}
