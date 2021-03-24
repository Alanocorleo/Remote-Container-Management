#Author: Group 2
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
Feature: Register clients
		I want to use this template for my feature file

  @tag1
  Scenario: A client is registering in the system with date of birth, full name and email, phone number
    Given a new client with name "Alice Allison" 
    And born 01012000  
    And with email "AliceAllison@gmail.com" 
    And with phone number 12345678
    When the company registers the client
    Then the client is registered in the system successfully
    And the client is assigned a unique company ID 1

