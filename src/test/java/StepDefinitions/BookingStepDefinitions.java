package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class BookingStepDefinitions extends Declarations {

    ExtentReports extent;
    ExtentTest test;

    @Given("a has logged in")
    public void a_has_logged_in() throws InterruptedException {
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Luyanda.Nene\\Intellij Projects\\BDD_Exercise1\\Reports\\bookingReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Booking");
    }

    @When("a user fillsSearchHotelForm {string} , {string}")
    public void a_user_fills_search_hotel_form(String sCheckInDate, String sCheckOutDate) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Enter location
        WebElement staticDropDown1 = getDriver().findElement(By.id("location"));
        Select dropdown1 = new Select(staticDropDown1);
        dropdown1.selectByIndex(2);

        //Enter hotel
        WebElement staticDropDown2 = getDriver().findElement(By.id("hotels"));
        Select dropdown2 = new Select(staticDropDown2);
        dropdown2.selectByIndex(2);

        //Enter room type
        WebElement staticDropDown3 = getDriver().findElement(By.id("room_type"));
        Select dropdown3 = new Select(staticDropDown3);
        dropdown3.selectByIndex(2);

        //Enter number of rooms
        WebElement staticDropDown4 = getDriver().findElement(By.id("room_nos"));
        Select dropdown4 = new Select(staticDropDown4);
        dropdown4.selectByIndex(2);

        getDriver().findElement(By.id("datepick_in")).sendKeys(sCheckInDate);
        getDriver().findElement(By.id("datepick_out")).sendKeys(sCheckOutDate);

        //Enter adults per room
        WebElement staticDropDown5 = getDriver().findElement(By.id("adult_room"));
        Select dropdown5 = new Select(staticDropDown5);
        dropdown5.selectByIndex(2);

        //Enter children per room
        WebElement staticDropDown6 = getDriver().findElement(By.id("child_room"));
        Select dropdown6 = new Select(staticDropDown6);
        dropdown6.selectByIndex(2);
    }

    @When("a user clicksSearch")
    public void a_user_clicks_search() {
        getDriver().findElement(By.id("Submit")).click();
    }

    @When("a user clicksContinue after selecting hotel")
    public void a_user_clicks_continue_after_selecting_hotel() {
        getDriver().findElement(By.id("radiobutton_0")).click();
        getDriver().findElement(By.id("continue")).click();
    }

    @When("a user fillsInBookingPage {string}, {string}, {string}, {string}, {string}")
    public void a_user_fills_in_booking_page(String sFirstName, String sLastName, String sBillingAddress, String sCreditCardNo, String sCVVNumber) {
        getDriver().findElement(By.id("first_name")).sendKeys(sFirstName);
        getDriver().findElement(By.id("last_name")).sendKeys(sLastName);
        getDriver().findElement(By.id("address")).sendKeys(sBillingAddress);
        getDriver().findElement(By.id("cc_num")).sendKeys(sCreditCardNo);
        if (sCreditCardNo.length() != 16) {
            test.fail("Credit card number is invalid (is not 16 digits)");
            test.assignDevice("Dell, Intel® Core™ i7");
            test.assignAuthor("Luyanda Nene");
        } else {
            test.pass("Credit card number is valid (is 16 digits)");
            test.assignDevice("Dell, Intel® Core™ i7");
            test.assignAuthor("Luyanda Nene");
        }

        //Enter credit card type
        WebElement staticDropDown7 = getDriver().findElement(By.id("cc_type"));
        Select dropdown7 = new Select(staticDropDown7);
        dropdown7.selectByIndex(2);

        //Select month
        WebElement staticDropDown8 = getDriver().findElement(By.id("cc_exp_month"));
        Select dropdown8 = new Select(staticDropDown8);
        dropdown8.selectByIndex(2);

        //Select year
        WebElement staticDropDown9 = getDriver().findElement(By.id("cc_exp_year"));
        Select dropdown9 = new Select(staticDropDown9);
        dropdown9.selectByVisibleText("2022");

        //Enter CVV number
        getDriver().findElement(By.id("cc_cvv")).sendKeys(sCVVNumber);
    }

    @When("a user clicksBookNowButton")
    public void a_user_clicks_book_now_button() throws InterruptedException {
        getDriver().findElement(By.id("book_now")).click();
        Thread.sleep(5000);
    }

    @Then("a user will have booked successfully")
    public void a_user_will_have_booked_successfully() {
        WebElement orderNum = getDriver().findElement(By.id("order_no"));
        if (!orderNum.isDisplayed()) {
            test.assignDevice("Dell, Intel® Core™ i7");
            test.assignAuthor("Luyanda Nene");
            test.fail("Booking unsuccessful");
            Assert.fail();
        } else {
            test.assignDevice("Dell, Intel® Core™ i7");
            test.assignAuthor("Luyanda Nene");
            test.pass("Booking successful");
        }
        driver.close();
        extent.flush();
    }

    @Then("a user will have booked unsuccessfully")
    public void aUserWillHaveBookedUnsuccessfully() {
        if (!driver.findElement(By.id("cc_num_span")).isDisplayed()) {
            test.fail("Booking unsuccessful");
            Assert.fail();
        } else {
            test.pass("Booking successful");
        }
        driver.close();
        extent.flush();
    }
}
