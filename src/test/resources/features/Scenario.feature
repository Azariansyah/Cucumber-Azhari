Feature: Item Management API

    Scenario Outline: As a user I can add new data
        Given A list of item are available
        When I add item to list "<payload>"
        Then The item is available

        Examples:
            |payload    |
            |addItem    |
            |addItem2   |