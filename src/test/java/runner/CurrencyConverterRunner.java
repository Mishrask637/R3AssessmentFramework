package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/CurrencyConverter.feature", glue = {
		"stepdefinitions" }, plugin = { "pretty", "json:target/cucumber-reports/json/cucumber-json-report.json",
				"html:target/cucumber-reports/html/currencyConverter.html" }, dryRun = false, monochrome = true)
public class CurrencyConverterRunner extends AbstractTestNGCucumberTests {
}
