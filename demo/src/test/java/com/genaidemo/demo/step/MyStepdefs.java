package com.genaidemo.demo.step;

import com.genaidemo.demo.annotation.LazyAutowired;
import com.genaidemo.demo.page.LoginPage;
import com.genaidemo.demo.page.chatGPTClient;
import com.genaidemo.demo.utility.MockTestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest
public class MyStepdefs {

    @LazyAutowired
    private LoginPage loginPage;
    @LazyAutowired
    private MockTestData mockTestData;

    @Given("{string} is able to open successfully Homepage for testcase {string}")
    public void userIsAbleToLoginSuccessfullyOntoHomepageFor(String userType, String testCase) {
        log.info(userType + " is going to open METLIFE HOMEPAGE for testCase " + testCase);
        try {
            this.loginPage.loginToPortal(testCase);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Then("User is on {string} Page for testCase {string}")
    public void userIsOnPageForTestCase(String page, String testCase) {
        log.info(page + " is going to open for testCase " + testCase);
        try {
            this.loginPage.goToPage(page, testCase);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Given("User is able to connect to ChatGPT API and sends {string} to get result")
    public void user_is_able_to_connect_to_chat_gpt_api_and_sends_hey_chat_gpt_to_get_result(String prompt) {
        // Write code here that turns the phrase above into concrete actions

        try {
            chatGPTClient chatGPTClientObject = new chatGPTClient();

            System.out.println(prompt);
            String response = chatGPTClientObject.chatGPTAPICall(prompt);

            System.out.println(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
