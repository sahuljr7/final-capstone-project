Feature: Checkout process

  Background:
    Given the user is logged in with username "standard_user" and password "secret_sauce"
    And the user has added items to the cart

  Scenario: Complete checkout with valid information
    When the user proceeds to checkout
    And the user enters first name "John", last name "Doe", and postal code "12345"
    And the user completes the purchase
    Then the order confirmation should be displayed
