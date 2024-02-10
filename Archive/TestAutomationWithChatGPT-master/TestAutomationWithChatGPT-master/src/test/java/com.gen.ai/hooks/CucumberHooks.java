package com.gen.ai.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CucumberHooks {

    @Autowired
    protected WebDriver driver;

    @Before
    public void setUp() {
        log.info("Before scenario...");

    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                log.info("Failed test:" + scenario.getName());
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
      // this.driver.close();
     //  this.driver.quit();
            } else {
                log.info("Passed test:" + scenario.getName());
             this.driver.close();
               this.driver.quit();
            }
        }catch (Exception e){
            log.error("error in Hooks :"+e);
        }
    }
}
