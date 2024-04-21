package hooks;

import java.io.IOException;

import cucumberhtmlreports.GenerateCucumberReports;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utility.Utility;

public class cucumberHooks {

	private static String cucumberReportsDir = "target/cucumberhtmlreports/";
	private static String cucumberJsonReportDir = "target/cucumber-reports/json/";
	private static String cucumberHtmlReportDir = "target/html/";

	@BeforeAll
	public static void deleteAllDirs() throws InterruptedException, IOException {
		Utility utility = new Utility();
		utility.deleteDir(cucumberReportsDir);
		Thread.sleep(2000);
		utility.deleteDir(cucumberJsonReportDir);
		Thread.sleep(2000);
		utility.deleteDir(cucumberHtmlReportDir);
		Thread.sleep(2000);
	}

	@AfterAll
	public static void generateReports() throws InterruptedException, IOException {
		GenerateCucumberReports generateReports = new GenerateCucumberReports();
		generateReports.generateCucumberReports();
	}

}
