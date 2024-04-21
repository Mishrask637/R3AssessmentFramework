@tag
Feature: Currency Converter Feature

  @tag1
  Scenario: Verify if the api is giving a valid reponse, Status and Status Code
    Given I have an API URL
    When I use get method to hit the api
    Then I get a valid json response with status "success"
    And I get total 162 currency pairs
    
  Scenario: Verify if the api is giving a invalid reponse
    Given I have an invalid API URL
    When I use get method to hit the api
    Then I get a invalid json response with status other than "Success"
    And I verify the error_type as "unsupported-code"

  @tag2
  Scenario Outline: Get the currency value in USD
    Given I have an API URL
    When I use get method to hit the api
    Then I verify the USD currency value for currency "<Currency>" should be <Value> and between the range <Range>

    Examples: 
     | Currency |	Value	 | Range   |
     | AED		  | 3.6725 | 3.6,3.7 |
     
  
  Scenario: Validate the api JSON schema
    Given I have an API URL
    When I use get method to hit the api
    Then I validate the json schema  
