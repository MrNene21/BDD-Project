package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class SearchStepDefs extends Declarations{
    public String orderNum;
    @And("user gets order number")
    public void userGetsOrderNumber() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        orderNum = getDriver().findElement(By.id("order_no")).getAttribute("value");
        System.out.println("Order number is: " + orderNum);
    }

    @And("user click my itinerary button")
    public void userClickMyItineraryButton() {
        getDriver().findElement(By.id("my_itinerary")).click();
    }

    @And("user searches for hotel")
    public void userSearchesForHotel() {
        getDriver().findElement(By.id("order_id_text")).sendKeys(orderNum);
        getDriver().findElement(By.id("search_hotel_id")).click();
    }

    @Then("user receives results")
    public void userReceivesResults() {
        WebElement result = getDriver().findElement(By.id("search_result_error"));
        if (!result.isDisplayed()){
            Assert.fail();
        }
    }
}
