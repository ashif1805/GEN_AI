package stepsDefinitions;

import io.cucumber.java.en.*;
import utils.chatGPTClient;

public class chatGPTAPISteps {

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
