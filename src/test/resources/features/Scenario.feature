Feature: Item Management API

  Scenario Outline: As a user I can add new data
    Given A list of products are available
    When I add item to list "<payload>"
    Then The item is available

    Examples:
      | payload   |
      | addItem   |
      | addItem2  |

  Scenario: As a user I can add a specific item
    Given A list of products are available
    When I add a specific item with name "Samsung Galaxy S21" and price 899.99
    Then The item with name "Samsung Galaxy S21" should be available