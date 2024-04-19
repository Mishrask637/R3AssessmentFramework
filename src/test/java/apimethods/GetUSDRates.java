package apimethods;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;	
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojoclasses.MainObject;

public class GetUSDRates {

//@Test	
public void getRates() {
		
		String base_url = "https://open.er-api.com/";
		
		given()
			.baseUri(base_url)
			.contentType(ContentType.JSON)
		.when()
			.get("v6/latest/USD")
		.then()
			.log().all();
		
	}
	
//@Test
public void getRatesAndStoreResponse() {
	
	String base_url = "https://open.er-api.com/";
	
	Response resp = given()
		.baseUri(base_url)
		.contentType(ContentType.JSON)
	.when()
		.get("v6/latest/USD");
	
		JsonPath jp = resp.jsonPath();
		
		System.out.println(jp.getString("rates.USD"));
	
}

@Test
public void extractResultInPojoClass() {
	
String base_url = "https://open.er-api.com/";
	
	MainObject obj = given()
		.baseUri(base_url)
		.contentType(ContentType.JSON)
	.when()
		.get("v6/latest/USD")
	.then()
		.extract().as(MainObject.class);
	
	Map<String,Object> ratesObj = obj.getRates();
	
	ratesObj.forEach((K,V)-> System.out.println("Key is "+ K + "\n Value is "+V));
	
	System.out.println("Number of currencies available are "+ratesObj.size());
	
	Assert.assertEquals(ratesObj.size(), 162);
	
	System.out.println("Result is "+obj.getResult());
	
	Assert.assertTrue(obj.getResult().equalsIgnoreCase("success"));
	
	System.out.println(" USD price against AED is "+ratesObj.get("AED"));
	
	Assert.assertEquals(ratesObj.get("AED"), 3.6725);
}

}
