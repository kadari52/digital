

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * This is the service layer Bean class. It provides the functionality required to read excel and csv
 * files from a given Input folder, filters the required excel and csv files using their mime-type.	 * 
 * It has the method to print filename, file mime type, file size, file extension. 
 * @author Kamalakar
 *
 */
public class serviceClass implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String folderName;
	private String outputFolder;
	private ArrayList<String> supportedFileList;
	
	public serviceClass() { }
	
	public String getOutputFolder() {
		return outputFolder;
	}

	/**
	 * Method to set the output folder.
	 * 
	 * Not used
	 * 
	 * @param outputFolder Output folder where the output csv file is generated
	 */
	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getFolderName() {
		return folderName;
	}

	/**
	 * Method to set the input folder
	 * @param folderName Input files folder name
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public ArrayList<String> getSupportedFileList() {
		return supportedFileList;
	}

	/**
	 * Method to set the required supported files using their mime type
	 * @param supportedFileList List of all files required to be processed
	 */
	public void setSupportedFileList(ArrayList<String> supportedFileList) {
		this.supportedFileList = supportedFileList;
	}

	public void listFiles() throws IOException
	{
		File f = new File(folderName);
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isFile()){
				System.out.println("File name:" + files[i].getName());
				System.out.println("File length in KB:" + files[i].length()/1024);
				System.out.println("File type:" + Files.probeContentType(files[i].toPath()));
				System.out.println("File extension:" + FilenameUtils.getExtension(files[i].getName()));
			}
		}	
	}
	
	/**
	 * Method to retrieve the excel and csv files
	 * @return
	 * @throws IOException
	 */
	public File[] getFiles() throws IOException
	{
		String mime_type;
		ArrayList<File> af = new ArrayList<File>();
		File f = new File(folderName);
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isFile()){
				mime_type = Files.probeContentType(files[i].toPath());
				if(supportedFileList.toString().contains(mime_type)){
					af.add(files[i]);
					System.out.println("File added to support list:" + files[i].getName());
				}
				System.out.println("File type:" + Files.probeContentType(files[i].toPath()));
				

			}
		}	
		return af.toArray(new File[af.size()]);
	}
	
	/**
	 * Method to retrieve test data from the excel file in the required format for the 
	 * Parameterised.class for the data driven test
	 *  
	 * @param fileName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	private ArrayList<ArrayList<String>> readRegNumberFromExcelFile(String fileName) throws InvalidFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(fileName);
		// Create a Apache POI workbook object for the excel file path provided
		Workbook workbook = WorkbookFactory.create(fis);
		Row row = null;
		
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		int rowCount = workbook.getSheetAt(0).getPhysicalNumberOfRows();
		for(int i=1;i<rowCount;i++)
		{
			row = workbook.getSheetAt(0).getRow(i);
			Iterator<Cell> it = row.iterator();
			ArrayList<String> rowdata = new ArrayList<String>();
			rowdata.add(fileName);
			while(it.hasNext())
			{
				rowdata.add(it.next().toString());
			}
			rdata.add(rowdata);
		}
		System.out.println("Rows: " + rdata.toString());
		return rdata;
		
	}
	
	/**
	 * Method to retrieve test data from the csv file in the required format for the 
	 * Parameterised.class for the data driven test
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private ArrayList<ArrayList<String>> readRegNumberFromCSVFile(String fileName) throws IOException
	{
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		    String line = br.readLine();
		    line = br.readLine();
		    //System.out.println("Line:" + line);
		    while (line != null) {
		        
		        System.out.println("Line:" + line);
			    String[] tokens = line.split(",");
				ArrayList<String> rowdata = new ArrayList<String>();
				rowdata.add(fileName);
			    for(int i=0;i<tokens.length;i++){
			    	rowdata.add(tokens[i]);
			    }
		        rdata.add(rowdata);
		        line = br.readLine();
		    }

		br.close();
		System.out.println("Rows: " + rdata.toString());
		return rdata;
		
	}
	
	
	/**
	 * Method to give a common interface for reading from a file using the principle of loose 
	 * coupling of Factory Design Pattern
	 * 
	 * Did not implement the Factory Design pattern as the test mentions only 1 service bean class 
	 * 
	 * @param fileName Input file name
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public ArrayList<ArrayList<String>>  readRegNumberFromFile(String fileName) throws InvalidFormatException, IOException
	{
		String fextn = FilenameUtils.getExtension(fileName);
		ArrayList<ArrayList<String>>  rdata = null;
		switch(fextn)
		{
			case "xlsx":
			case "xls": rdata = readRegNumberFromExcelFile(fileName);
						break;
			case "csv": rdata = readRegNumberFromCSVFile(fileName);
						break;
		}
		return rdata;
	}
	
	/**
	 * Method to write the output csv file which can be used to cross verify the results 
	 * with the screen shots taken and input files
	 * @param make Make of the car
	 * @param colour Colour of the car
	 * @param regNo Registration Number of the car
	 * @param fileName Input File name
	 * @throws IOException
	 */
	public void updateDataInFile(String make, String colour, String regNo, String fileName) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("Output_File.csv",true));		
		StringBuffer bf = new StringBuffer();
		bf.append(fileName).append(",").append(regNo).append(",").append(make)
			.append(",").append(colour);
		bw.write(bf.toString());
		bw.newLine();
		bw.close();

	}
	
	

}
