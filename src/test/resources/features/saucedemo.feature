Feature: Full Saucedemo Navigation

    Background: Go to the Login page
        Given user navigates to the saucedemo page

    Scenario Outline: About page and back Navigation
        Given user logs in with a standard <username> and <password>
        When user navigates to the about page
        Then user should be in the saucelabs page

        When user navigates back
        Then user should be back in the all products page

        Examples:
        | username                | password     |
        | standard_user           | secret_sauce |
        | problem_user            | secret_sauce |
        | performance_glitch_user | secret_sauce |


    Scenario: Order products and go through the full buying process
        Given user logs in with as the standard_user
        When user order products from lower to higher price
        And user chooses the four most expensive products
        Then user sees a 4 on the cart icon

        When user goes to his cart
        And user does checkout
        And user comfirms the purchase
        Then user sees a success message
