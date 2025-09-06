Feature: User logs into SauceDemo

  Background:
    Given the user is on the SauceDemo login page

  Scenario: Successful login with valid credentials
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be on the Products page

  Scenario: Login with invalid credentials
    When the user logs in with username "invalid_user" and password "wrong_pass"
    Then an error message should be displayed
