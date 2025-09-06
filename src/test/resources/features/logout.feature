Feature: Logout functionality

  Background:
    Given the user is logged in with username "standard_user" and password "secret_sauce"

  Scenario: Logout from products page
    When the user logs out
    Then the user should be redirected to the login page
