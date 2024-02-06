import org.junit.runner.RunWith;
import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
        glue={"stepsDefinitions"},
monochrome = true,
plugin = {"pretty", "html:target/htmlReport"},
tags = "@ChatGPTAPITest")
public class testRunner {
}
