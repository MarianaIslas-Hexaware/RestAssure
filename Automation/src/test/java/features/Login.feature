Feature: Application Login

Scenario: Home page default login
Given User is on NetBanking landing page
When login into application with username "jin" and password "1234"
Then Home page is populated
And Cards are displayed "true"


Scenario: Home page default login disabled user
Given User is on NetBanking landing page
When login into application with username "John" and password "4321"
Then Home page is populated
And Cards are displayed "false"