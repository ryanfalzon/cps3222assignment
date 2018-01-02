package task2.StepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class WebAppStepDefs {

    // Properties
    WebDriver browser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }

    @Given("^I am an agent trying to log in$")
    public void i_am_an_agent_trying_to_log_in() throws Throwable{
        browser.get("localhost:8080/");
    }

    @When("^I obtain a key from the supervisor using a valid id$")
    public void i_obtain_a_key_from_the_supervisor_using_a_valid_id(String agentID, String agentName) throws Exception{
        browser.findElement(By.name("contactButton")).click();
        browser.findElement(By.name("id")).sendKeys(agentID);
        browser.findElement(By.name("name")).sendKeys(agentName);
        browser.findElement(By.name("getKeyButton")).click();
    }

    @Then("^the supervisor should give me a valid key$")
    public void the_supervisor_should_give_me_a_valid_key() throws Exception{
        assertEquals("Request Approved", browser.findElement(By.name("approved")));
    }

    @When("^I log in using that key$")
    public void i_log_in_using_that_key() throws Exception{
        String id;
        String loginKey;
        browser.findElement(By.name("backButton")).click();
        browser.findElement(By.name("loginButton")).click();
        browser.
    }
}