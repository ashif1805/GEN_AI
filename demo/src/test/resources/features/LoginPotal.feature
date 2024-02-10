Feature: LOGIN

  @loginTest1
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "RIS" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_1 |

  @loginTest2
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "Access" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_2 |

  @loginTest3
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "Find Dentist" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_3 |

  @loginTest4
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "Open Enrollment" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_4 |

  @loginTest5
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "Mypets" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_5 |

  @loginTest6
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "Vision provider" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_6 |

  @loginTest7
  Scenario Outline: Verify User is able to open into Metlife Portal
    Given "User" is able to open successfully Homepage for testcase "<TcNo>"
    Then User is on "Contact Us" Page for testCase "<TcNo>"
    Examples:
      | TcNO    |
      | Login_7 |

  @ChatGPTAPITest
  Scenario Outline: Test ChatGPT API and print results
    Given User is able to connect to ChatGPT API and sends "<Prompt>" to get result

    Examples:
      | TestCase    |  Prompt                                                   |
      | TC01        |  Generate a name of an Indian. It should have 2 words.    |

