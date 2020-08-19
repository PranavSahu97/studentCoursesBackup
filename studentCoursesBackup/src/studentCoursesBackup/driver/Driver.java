package studentCoursesBackup.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;    
/**
 * @author Pranav Sahu
 *
 */
    
    public class Driver {
    	public static String inputFilePath;
		public static String deleteFilePath;
		public static String outputFilePath1;
		public static String outputFilePath2;
		public static String outputFilePath3;
		
	public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException, NumberFormatException {
	    
	    /*
	     * As the build.xml specifies the arguments as argX, in case the
	     * argument value is not given java takes the default value specified in
	     * build.xml. To avoid that, below condition is used
	     */

	    // FIXME: update this if statement for this assignment
	    if ( (args.length != 5) || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}") || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {
		    
		    System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
		    System.exit(0);
	    } // end of if
	    
	    inputFilePath = args[0];
		deleteFilePath = args[1];
		outputFilePath1 = args[2];
		outputFilePath2 = args[3];
		outputFilePath3 = args[4];
		
		Results results = new Results();
		Results resultsClone1 = new Results();
		Results resultsClone2 = new Results();
		TreeBuilder helper = new TreeBuilder();
		try {
			helper.fileInsert();
			helper.fileDelete();
			helper.printNodes(results,resultsClone1,resultsClone2);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("IO Exception");
			System.exit(0);
		}
	}  // end public static void main
}  // end public class Driver
