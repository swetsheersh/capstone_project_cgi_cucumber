Feature: Flipkart.com

  Background:
    Given Launch Browser
    And navigate to the Flipkart URL

  Scenario: Check login with valid credentials
    When click on the Login button
    Then verify login page as "<currenturl>"
    And enter valid phone number as "<phoneno>" and verify result should be "<Expected>"
    And click the Login button
    Then RequestOTP should be displayed

    Examples:
      | currenturl                                                | phoneno    | Expected |
      | https://www.flipkart.com/account/login?ret=%2Faccount%2F%3Frd%3D0%26link%3Dhome_account | 6207712568 | TRUE     |

  Scenario: Verify searching for a product
    When search for the product as "<data>"
    And click on the Submit Search button
    Then click on the iPhone
    And verify the product name as "<productname>", price as "<price>", title as "<title>", and result should be "<Expected>"

    Examples:
      | data      | productname | price | title | Expected |
      | Iphone 14 | APPLE iPhone 14 (Midnight, 128 GB) | 67,999 | APPLE iPhone 14 ( 128 GB Storage ) Online at Best Price On Flipkart.com | FALSE |

  Scenario: Verify a product is added to the cart
    When search for the product "<pro>"
    And click the Search button
    Then Add to Cart
    And Product Should be added on URL as "<url>", have as "<lik>", and expected as "<exp>"

    Examples:
      | pro    | url                                                    | lik           | exp   |
      | Mobile | https://www.flipkart.com/viewcart?exploreMode=true&preference=FLIPKART | Total Amount | TRUE  |

  Scenario: Filter and Verify Apple iPhone 14
    When navigate to mobile section
    And filter for Apple products
    Then click on the first product
    And verify product name containing "<productname>" and Title as "<title>", and verified result should be "<Expected>"

    Examples:
      | productname    | title            | Expected |
      | APPLE iPhone 14 | APPLE iPhone 14 | TRUE     |

  Scenario: Verify product removed from cart
    When search the Product
    And add the product to my cart
    Then remove the product from my cart
    And verify message as "<message1>" and cart as "<message2>" and result as "<Expected>"
    And close the Browser

    Examples:
      | message1           | message2 | Expected |
      | Missing Cart items? | Login    | TRUE     |