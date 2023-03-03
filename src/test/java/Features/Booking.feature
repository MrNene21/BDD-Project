@Booking
Feature: Booking
  A feature to book successfully

  Background: Login successful
    Given a user is on the home page
    When  a user navigates to the Login page using "http://adactinhotelapp.com/index.php"
    And a user enter "Luyanda23" and "Luyanda2023"
    And a user clicks the login button
    Then a user is loggen in successfully

    Scenario Outline: Booking successful
      Given a has logged in
      When a user fillsSearchHotelForm "<checkInDate>" , "<checkOutDate>"
      And a user clicksSearch
      And a user clicksContinue after selecting hotel
      And a user fillsInBookingPage "<firstName>", "<lastName>", "<billingAddress>", "<creditCardNo>", "<CVVNumber>"
      And a user clicksBookNowButton
      Then a user will have booked successfully
      Examples:
        | checkInDate | checkOutDate | firstName | lastName | billingAddress | creditCardNo | CVVNumber |
        |   01/03/2023  |   03/03/2023   | Luyanda | Nene | Cape Town | 1234567890123456 | 123 |

  Scenario Outline: Booking unsuccessful
    Given a has logged in
    When a user fillsSearchHotelForm "<checkInDate>" , "<checkOutDate>"
    And a user clicksSearch
    And a user clicksContinue after selecting hotel
    And a user fillsInBookingPage "<firstName>", "<lastName>", "<billingAddress>", "<creditCardNo>", "<CVVNumber>"
    And a user clicksBookNowButton
    Then a user will have booked successfully
    Examples:
      | checkInDate | checkOutDate | firstName | lastName | billingAddress | creditCardNo | CVVNumber |
      |   01/03/2023  |   03/03/2023   | Luyanda | Nene | Cape Town | 1234 | 123 |



