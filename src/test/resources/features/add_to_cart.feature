Feature: Add items to cart

  Background:
    Given the user is logged in with username "standard_user" and password "secret_sauce"

  Scenario: Add a single item to cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart should contain 1 item

  Scenario: Add multiple items to cart
    When the user adds "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then the cart should contain 2 items
