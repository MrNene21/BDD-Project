package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginStepdefs extends Declarations{
    ExtentReports extent;
    ExtentTest test;
    @Given("a user is on the home page")
    public void aUserIsOnTheHomePage() {
        initWebDriver();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
        getDriver().manage().window().maximize();

        //report
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Luyanda.Nene\\Intellij Projects\\BDD_Exercise1\\Reports\\reports.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Login");
    }

    @When("a user navigates to the Login page using {string}")
    public void aUserNavigatesToTheLoginPageUsing(String sURL) {
        getDriver().get(sURL);
    }

    @And("a user enter {string} and {string}")
    public void aUserEnterAnd(String sUsername, String sPassword) {
        getDriver().findElement(By.id("username")).sendKeys(sUsername);
        getDriver().findElement(By.id("password")).sendKeys(sPassword);
    }

    @And("a user clicks the login button")
    public void aUserClicksTheLoginButton() {
        getDriver().findElement(By.id("login")).click();
    }

    @Then("a user is loggen in successfully")
    public void aUserIsLoggenInSuccessfully() throws InterruptedException {
        Thread.sleep(5000);
        if(!getDriver().findElement(By.id("location")).isDisplayed()){
            test.assignDevice("Dell, Intel® Core™ i7");
            test.assignAuthor("Luyanda Nene");
            test.fail("Login unsuccessful");
            Assert.fail();
        }
        else {
            test.assignDevice("Dell, Intel® Core™ i7");
            test.assignAuthor("Luyanda Nene");
            test.pass("Login is successful!");
        }
        extent.flush();
    }
}
