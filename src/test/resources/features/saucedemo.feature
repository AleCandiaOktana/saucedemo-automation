Feature: Full Saucedemo Navigation

    Background: Login Step
        Scenario Outline: user login
            Given user navigates to the saucedemo page
            And user logs in with a standard <username> and <password>

            Examples: 
            | username                | password     |
            | standard_user           | secret_sauce |
            | problem_user            | secret_sauce |
            | performance_glitch_user | secret_sauce |

    Scenario: About page and back
        When user navigates to the about page
        Then user should be in the saucelabs page

        When user navigates back
        Then user should be back in the all products page 

    Scenario: Order products and go through the full buying process
        When user order products from highest to lowest price
        And user chooses the 4 more expensive products
        Then user sees a 4 on the cart icon

        When user goes to his cart
        And user does checkout
        And user comfirms/finalizes the purchase
        Then user sees a success message

    # Scenario: Order products and go through the full buying process
    #     When user order products from highest to lowest price
    #     And user chooses the 4 more expensive products
    #     Then user sees a 4 on the cart icon

    #     When user goes to his cart
    #     And user does checkout
    #     And user comfirms/finalizes the purchase
    #     Then user sees a success message

