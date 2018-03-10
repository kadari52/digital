

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * This class will have the test for checking vehicle information. 
 * 
 * It uses the Parameterized.class to do a data driven test. 
 * The data is read from the excel and csv files from a given input folder.
 * 
 * @author Kamalakar
 *
 */
@RunWith(Parameterized.class)
public class Question1 extends TestBase
{
	private String fileName;
	private String regno;
	private String make;
	private String colour;
	//private String makeMatch;
	//private String colourMatch;
	
	public Question1(String fileName, String regno, String make, String colour) {
		super();
		this.fileName = fileName;
		this.regno = regno;
		this.make = make;
		this.colour = colour;
	}
	
	/**
	 * This is the method which is called first during the test execution. This method uses the Service
	 * Layer class serviceClass class in src/main/java.
	 * 
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Parameters
	public static Collection<Object[]> testData() throws InvalidFormatException, IOException
	{	
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		ArrayList<Object[]> odata = new ArrayList<Object[]>();
		serviceClass dataClass = new serviceClass();
		//dataClass.setFolderName(testConfig.getProperty("dataFileFolder"));
		dataClass.setFolderName("C:\\Data\\DataFiles");
		ArrayList<String> sfiles = new ArrayList<String>();
		sfiles.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		sfiles.add("application/vnd.ms-excel");
		dataClass.setSupportedFileList(sfiles);
		File[] files = dataClass.getFiles();
		for(File f : files){
			ArrayList<ArrayList<String>> tdata = dataClass.readRegNumberFromFile(f.getCanonicalPath());
			for(int i=0;i<tdata.size();i++){
				ArrayList<String> tt = tdata.get(i);
				System.out.println("TT:" + tt.toString());
				Object[] kk = (tt.toArray(new String[tt.size()]));
				//String[] pp = (tt.toArray(new String[tt.size()]));
				//System.out.println("kk:" + kk[1].toString());				
				odata.add(kk);
			}
		}
		
		return odata;
		
	}
	

	/**
	 * This is the test method. It is written in POM style
	 * Registration Number is passed to the Vehicle Enquiry Page
	 * 
	 * The expected test data read from the files is checked against the Vehicle Enquiry Results Page
	 * Expected values passed are Make, Colour
	 * 
	 * Screen Shot name is passed to store the Snapshot taken after the test
	 * 
	 * Input file name and Registration number is also passed to generate a output report file in 
	 * CSV file
	 * 
	 * @throws IOException
	 */
	@Test
	public void checkVehicleInfo() throws IOException
	{
		Boolean testResult;
		
		testResult = homePage
					.startCheck()
					.keyInVehicleReg(regno)
					.isVehicleDetailsMatch(fileName,make,colour,regno,testConfig.getProperty("testEvidenceFolder")+"\\" + regno + ".jpg");
		
		Assert.assertTrue("Vehicle Details Match",testResult);
				
	}
}
