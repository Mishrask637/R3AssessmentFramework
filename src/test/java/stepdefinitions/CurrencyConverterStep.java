package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import pojoclasses.MainObject;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.Assert;

public class CurrencyConverterStep {

	private static String base_url = "";
	private static String endPoint = "";
	private Response resp = null;

	@Given("I have an API URL")
	public void i_have_an_api_url() {

		base_url = "https://open.er-api.com/";
		endPoint = "v6/latest/USD";
	}

	@Given("I have an invalid API URL")
	public void i_have_an_invalid_api_url() {

		base_url = "https://open.er-api.com/";
		endPoint = "v6/latest/ABC";
	}

	@When("I use get method to hit the api")
	public void i_use_get_method_to_hit_the_api() {

		resp = given().baseUri(base_url).contentType(ContentType.JSON).when().get(endPoint);
	}

	@Then("I get a valid json response with status {string}")
	public void i_get_a_valid_json_response_with_status(String status) {
		// printing the status line
		System.out.println(resp.getStatusLine());
		// validate API status code
		Assert.assertEquals(resp.getStatusCode(), 200);
		// validate api returns success status
		String result = resp.body().jsonPath().getString("result");
		Assert.assertEquals(result, status);
		// validate api returns valid price
		int USDVal = (int) resp.as(MainObject.class).getRates().get("USD");
		Assert.assertEquals(USDVal, 1);
	}

	@Then("I get a invalid json response with status other than {string}")
	public void i_get_a_invalid_json_response_with_status_other_than(String result) {
		// verify the api status
		String actualResult = resp.body().jsonPath().get("result");
		Assert.assertNotEquals(actualResult, result);
	}

	@Then("I verify the error_type as {string}")
	public void i_verify_the_error_type_as(String errorType) {
		// verify the error-type
		String actualErrorType = resp.body().jsonPath().getString("error-type");
		Assert.assertEquals(actualErrorType, errorType);
	}

	@Then("I get total {int} currency pairs")
	public void i_get_total_currency_pairs(int count) {
		// validate total currency pairs
		MainObject obj = resp.as(MainObject.class);
		int currencyCount = obj.getRates().size();
		Assert.assertEquals(currencyCount, count);
	}

	@Then("I verify the USD currency value for currency {string} should be {double} and between the range {double},{double}")
	public void i_verify_the_usd_currency_value_for_aed_should_be(String currency, double value, double range1,
			double range2) {
		// verify the uds currency against AED and verify price range
		MainObject obj = resp.as(MainObject.class);
		double actualCurrencyVal = (Double) obj.getRates().get(currency);
		Assert.assertEquals(actualCurrencyVal, value);
		Assert.assertTrue((actualCurrencyVal > range1) && (actualCurrencyVal < range2),
				actualCurrencyVal + " should be between " + range1 + " & " + range2);
	}

	@Then("I validate the json schema")
	public void i_validate_the_json_schema() {
		try {
			resp.then().body(JsonSchemaValidator
					.matchesJsonSchema(new FileInputStream(new File("./src/test/resources/schema/jsonSchema.json"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
