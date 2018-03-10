

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Used for checking the service class. Not part of the test
 * 
 * @author Kamalakar
 *
 */
public class serviceCall {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		// TODO Auto-generated method stub
/*		serviceClass sc = new serviceClass();
		System.out.println("Starting now...");
		sc.setFolderName("C:\\Data\\DataFiles");
		sc.listFiles();
		ArrayList<String> sfiles = new ArrayList<String>();
		sfiles.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		sfiles.add("application/vnd.ms-excel");
		sc.setSupportedFileList(sfiles);
		File[] files = sc.getFiles();
		for(File f : files){
			System.out.println(f.getPath());
			sc.readRegNumberFromFile(f.getCanonicalPath());
		}*/
		
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		ArrayList<Object[]> odata = new ArrayList<Object[]>();
		serviceClass dataClass = new serviceClass();
		dataClass.setFolderName("C:\\Data\\DataFiles");
		
		ArrayList<String> sfiles = new ArrayList<String>();
		//sfiles.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
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
				System.out.println("kk:" + kk[1].toString());				
				odata.add(kk);
			}
		}
		
		//odata = (Collection<Object[]>) rdata;
		System.out.println(odata.toString());
	}

}
