Feature: Remove items from cart

  Background:
    Given the user is logged in with username "standard_user" and password "secret_sauce"
    And the user has items in the cart

  Scenario: Remove a single item from cart
    When the user removes "Sauce Labs Backpack" from the cart
    Then the cart should not contain "Sauce Labs Backpack"
