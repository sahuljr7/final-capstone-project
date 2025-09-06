Feature: Filter and sort products

  Background:
    Given the user is logged in with username "standard_user" and password "secret_sauce"

  Scenario: Sort products by price low to high
    When the user sorts products by "Price (low to high)"
    Then the first product should be the cheapest
