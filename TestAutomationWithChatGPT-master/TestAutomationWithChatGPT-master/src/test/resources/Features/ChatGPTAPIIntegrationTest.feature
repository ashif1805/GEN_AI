Feature: Test ChatGPT API Functionality and get results

  @ChatGPTAPITest
  Scenario Outline: Test ChatGPT API and print results
    Given User is able to connect to ChatGPT API and sends "<Prompt>" to get result

    Examples:
    | TestCase    |  Prompt                                                   |
    | TC01        |  Generate a name of an Indian. It should have 2 words.    |
