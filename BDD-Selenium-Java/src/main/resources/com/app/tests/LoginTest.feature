Feature: Logging into BOL

In order to work
As a user
I want to login

Background:
	Given I go to businessOnline application

@smoke
Scenario Outline: Logging in BOL With Invalid Credentials
When I login to application with invalid "<username>" and "<password>"
Then Login should be "<ExpectedResult>"

Examples:
|username 								| password | ExpectedResult |
|varaprasad.pakalapati@gmail.com	   | P@ssword | Failed 	   |

@smoke
Scenario Outline: Logging in BOL
When I login to application with "<username>" and "<password>"
Then Login should be "<ExpectedResult>"

Examples:
| username 								| password | ExpectedResult |
| varaprasad.pakalapati@gmail.com	   | P@ssword1 | Success 	   |