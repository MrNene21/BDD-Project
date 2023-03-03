@Search
Feature: Search
  A feature to book search for a hotel

  Background: Login successful
    Given a user is on the home page
    When  a user navigates to the Login page using "http://adactinhotelapp.com/index.php"
    And a user enter "Luyanda23" and "Luyanda2023"
    And a user clicks the login button
    Then a user is loggen in successfully

  Scenario: Booking and search
    Given a has logged in
    When a user fillsSearchHotelForm "01/03/2023" , "03/03/2023"
    And a user clicksSearch
    And a user clicksContinue after selecting hotel
    And a user fillsInBookingPage "Luyanda", "Nene", "Kempton Park", "1234567890124589", "567"
    And a user clicksBookNowButton
    And user gets order number
    And user click my itinerary button
    And user searches for hotel
    Then user receives results


