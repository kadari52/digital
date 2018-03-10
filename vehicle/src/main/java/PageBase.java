

import org.openqa.selenium.WebDriver;

/**
 * This is the base class for all the pages
 * 
 * @author Kamalakar
 *
 */
public class PageBase {
	
	protected WebDriver driver;
	
	public PageBase(WebDriver driver)
	{
		this.driver = driver;
	}

}
