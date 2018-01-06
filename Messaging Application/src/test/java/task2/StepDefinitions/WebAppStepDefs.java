package task2.StepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebAppStepDefs {

    // Properties
    WebDriver browser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }

    @Given("^I am an agent trying to log in$")
    public void i_am_an_agent_trying_to_log_in() throws Throwable {
        browser.get("localhost:8080/");
    }

    @When("^I obtain a key from the supervisor using a valid id \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_obtain_a_key_from_the_supervisor_using_a_valid_id(String agentID, String agentName) throws Exception {
        browser.findElement(By.name("contactButton")).click();
        browser.findElement(By.name("id")).sendKeys(agentID);
        browser.findElement(By.name("name")).sendKeys(agentName);
        browser.findElement(By.name("getKeyButton")).click();
    }

    @Then("^the supervisor should give me a valid key$")
    public void the_supervisor_should_give_me_a_valid_key() throws Exception {
        assertEquals("Request Approved", browser.findElement(By.name("approval")).getText());
    }

    @When("^I log in using that key$")
    public void i_log_in_using_that_key() throws Exception {
        String id = browser.findElement(By.name("id")).getText().toString().substring(10);
        String loginKey = browser.findElement(By.name("loginkey")).getText().toString().substring(11);
        browser.findElement(By.name("backButton")).click();
        browser.findElement(By.name("loginButton")).click();
        browser.findElement(By.name("id")).sendKeys(id);
        browser.findElement(By.name("loginkey")).sendKeys(loginKey);
        browser.findElement(By.name("loginbutton")).click();
    }

    @Then("^I should be allowed to log in \"([^\"]*)\"$")
    public void i_should_be_allowed_to_log_in(String expectedTitle) throws Exception {
        assertEquals(expectedTitle, browser.getTitle());
    }

    @When("^I wait for (\\d+) seconds$")
    public void i_wait_for_seconds(String time) throws Exception {
        Thread.sleep(Integer.parseInt(time) * 1000);
    }

    @Then("^I should not be allowed to log in \"([^\"]*)\"$")
    public void i_should_not_be_allowed_to_log_in(String error) throws Exception {
        assertEquals(error, browser.findElement(By.name("error")).getText());
    }

    @Given("^I am a logged in agent$")
    public void i_am_a_logged_in_agent() throws Exception {

        // Login the agent
        browser.get("localhost:8080/");
        browser.findElement(By.name("contactButton")).click();
        browser.findElement(By.name("id")).sendKeys("001");
        browser.findElement(By.name("name")).sendKeys("Jane Doe");
        browser.findElement(By.name("getKeyButton")).click();
        String id = browser.findElement(By.name("id")).getText().substring(10);
        String loginKey = browser.findElement(By.name("loginkey")).getText().substring(11);
        browser.findElement(By.name("backButton")).click();
        browser.findElement(By.name("loginButton")).click();
        browser.findElement(By.name("id")).sendKeys(id);
        browser.findElement(By.name("loginkey")).sendKeys(loginKey);
        browser.findElement(By.name("loginbutton")).click();
    }

    @When("^I attempt to send (\\d+) messages$")
    public void i_attempt_to_send_messages(int amount) throws Exception {
        for(int i = 0; i < amount; i++){
            browser.findElement(By.name("targetagent")).sendKeys(Integer.toString(i));
            browser.findElement(By.name("message")).sendKeys("Hello, how are you?");
            browser.findElement(By.name("submitmessage")).click();
        }
    }

    @Then("^the messages should be successfully sent$")
    public void the_messages_should_be_successfully_sent() throws Exception {
        assertEquals("Message Sent", browser.findElement(By.name("error")).getText());
    }

    @When("^I try to send another message$")
    public void i_try_to_send_another_message() throws Exception {
        browser.findElement(By.name("targetagent")).sendKeys("26");
        browser.findElement(By.name("message")).sendKeys("Hello, how are you?");
        browser.findElement(By.name("submitmessage")).click();
    }

    @Then("^the system will inform me that I have exceeded my quota \"([^\"]*)\"$")
    public void the_system_will_inform_me_that_I_have_exceeded_my_quota(String arg1) throws Exception {
        assertEquals(arg1, browser.getTitle());
    }

    @Then("^I will be logged out \"([^\"]*)\"$")
    public void i_will_be_logged_out(String title) throws Exception {
        browser.findElement(By.name("automaticlogout")).click();
        assertEquals(title, browser.getTitle());
    }

    @When("^I click on log out$")
    public void i_click_on_log_out() throws Exception {
        browser.findElement(By.name("logoutButton")).click();
    }

    @Then("^I should be logged out \"([^\"]*)\"$")
    public void i_should_be_logged_out(String pageTitle) throws Exception {
        assertEquals(pageTitle, browser.getTitle());
    }
}