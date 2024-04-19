#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Currency Converter Feature

  @tag1
  Scenario: Verify if the api is giving a valid reponse
    Given I have an API URL
    When I use get method to hit the api
    Then I get a valid json response with status "success"

  @tag2
  Scenario Outline: Get the currency value in USD
    Given I have an API URL
    When I use get method to hit the api
    Then I verify the USD currency value for currency "<Currency>" should be <Value>

    Examples: 
     | Currency |	Value	 |
     | AED		  | 3.6725 |
