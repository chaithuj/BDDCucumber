Feature: Title of your feature

  @testui
  Scenario Outline: Title of your scenario outline
    Given User hit the URL "<URL>"
		And User click on anthem button
		And User click on member

    Examples: 
      | URL 			 | 
      | urlFromCSV |

        @testui1
  Scenario Outline: Title of your scenario outline
    Given User hit the URL "<URL>" from Excel
		And User click on anthem button
		And User click on member

    Examples: 
      | URL 		     | 
      | urlFromExcel |