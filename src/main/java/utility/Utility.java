package utility;
import java.io.*;

public class Utility {

	public static void main(String[] args) throws IOException {
		deleteDir(System.getProperty("user.dir")+"\\target\\cucumber-reports\\json\\cucumber-json-report.json");
	}

	public static void deleteDir(String dir) {
		File directory = new File(dir);
		if (directory.exists()) {
			directory.delete();
		}
	}

	public void createDir(String dir) {
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	public static void reFormatJsonFile() throws IOException {

		File jsonFile = new File("C:\\Users\\mishr\\eclipse-workspace\\R3AssessmentFramework\\target\\cucumber-reports\\json\\cucumber-json-report.json");
		byte[] jsonBytes = new byte[(int) jsonFile.length()];
		FileInputStream fis = new FileInputStream(jsonFile);
		fis.read(jsonBytes);
		fis.close();

		// Write the byte array to another JSON file
		File newJSONFile = new File("C:\\Users\\mishr\\eclipse-workspace\\R3AssessmentFramework\\target\\cucumber-reports\\json\\Cucumber-Json-Reports.json");
		FileOutputStream fos = new FileOutputStream(newJSONFile);
		fos.write(jsonBytes);
		fos.close();
	}
}