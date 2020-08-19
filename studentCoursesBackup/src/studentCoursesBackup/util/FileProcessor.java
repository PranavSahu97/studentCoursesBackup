package studentCoursesBackup.util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import studentCoursesBackup.driver.Driver;

public class FileProcessor {
	public FileProcessor(String filePath) {
		try {
			TreeBuilder.br = new BufferedReader(new FileReader(filePath));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}	

	public String readLine(BufferedReader br) {
	String line = "";
	try {
		line = br.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return line;
}
}
