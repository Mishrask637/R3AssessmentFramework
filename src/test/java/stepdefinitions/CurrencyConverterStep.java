package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoclasses.MainObject;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class CurrencyConverterStep {
	
	private static String base_url;
	private static String endPoint;
	Response resp;
	@Given("I have an API URL")
	public void i_have_an_api_url() {
	    
		base_url = "https://open.er-api.com/";
		endPoint = "v6/latest/USD";		
	}

	@When("I use get method to hit the api")
	public void i_use_get_method_to_hit_the_api() {
	    
	    resp = given()
	    	.baseUri(base_url)
	    	.contentType(ContentType.JSON)
	    .when()
	    	.get(endPoint);
	}

	@Then("I get a valid json response with status {string}")
	public void i_get_a_valid_json_response_with_status(String status) {
	    
	    String result = resp.body().jsonPath().getString("result");
	    Assert.assertEquals(result, status);
	}

	@Then("I verify the USD currency value for currency {string} should be {double}")
	public void i_verify_the_usd_currency_value_for_aed_should_be(String currency,Double value) {
	    
	   MainObject obj = resp.as(MainObject.class);
	   Assert.assertEquals(obj.getRates().get(currency), value);
	}

}
