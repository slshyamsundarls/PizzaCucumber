Feature: Customer must be able to order pizza of variable choice

  Scenario Outline: Customer must to able to choose different variation of pizza from menu
    Given menu is displayed
    When customer makes their choice of crust
    And customer makes their choice of toppings
    And customer makes their choice of order
    Then order total should be displayed
