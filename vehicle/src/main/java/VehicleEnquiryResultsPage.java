

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.TakesScreenshot;

/**
 * This is another page class. Its the results page
 * Screen shot of this page is taken and stored in the given Test Evidence folder
 * This class also has the functionality to generate a csv output file
 * 
 * @author Kamalakar
 *
 */
public class VehicleEnquiryResultsPage extends PageBase{

	public VehicleEnquiryResultsPage(WebDriver driver) {
		super(driver);

	}
	
	/**
	 * @param fileName Input file name
	 * @param make Make of the car
	 * @param colour Colour of the car
	 * @param regNo Car Registration Number
	 * @param screenshotName Name of the file name where screenshot is stored
	 * @return
	 * @throws IOException
	 * 
	 * This method is called from the test to do the verification of the expected values vs the 
	 * actual values from the results page
	 */
	public boolean isVehicleDetailsMatch(String fileName, String make, String colour, String regNo,String screenshotName) throws IOException
	{

		boolean makeMatched = false;
		String makeMatch = "Make Not Matched";
		boolean colourMatched = false;
		String colourMatch = "Colour Not Matched";

		List<WebElement> wlist = driver.findElements(By.className("list-summary-item"));
		System.out.println(wlist.size());
		Iterator<WebElement> itr = wlist.iterator();

		while(itr.hasNext()) {
			String searchStr = itr.next().getText();
		    System.out.println(searchStr);
		    if(searchStr.toUpperCase().contains(make.toUpperCase())){
		    	System.out.println("Make Matches");
		    	makeMatched = true;
		    	makeMatch = "Make Matched";
		    }
		    if(searchStr.toUpperCase().contains(colour.toUpperCase())){
		    	System.out.println("Colour Matches");
		    	colourMatched = true;
		    	colourMatch = "Colour Matched";
		    }
		}
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(screenshotName));
		
		serviceClass sc = new serviceClass();
		sc.updateDataInFile(makeMatch, colourMatch, regNo, fileName);

		if(makeMatched && colourMatched)
			return true;
		else
			return false;
		
	}

}
