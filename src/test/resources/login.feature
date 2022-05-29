Feature: Login page

  Scenario Outline: Customer must be able to login to their account
    Given user navigates to login page
    When user enters username and password from the "<DataSheet>" and rownumber "<RowNumber>"
    And user clicks the login button
    Then user must to navigated to home page

    Examples: 
      | DataSheet | RowNumber |
      | slshyamsundar | sshyam9104094 |
      
