package com.genaidemo.demo.config;

import com.genaidemo.demo.annotation.LazyAutowired;
import com.genaidemo.demo.annotation.LazyConfiguration;
import com.genaidemo.demo.annotation.ThreadScopeBean;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.HashMap;

@Slf4j
@LazyConfiguration
public class WebDriverConfig {
    public static WebDriver driver = null;
    @LazyAutowired
    private AppConfig environmentProperties;

    @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver chromeDriver() {
        if ("Chrome".equalsIgnoreCase(environmentProperties.getBrowser())) {
            //WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            HashMap<String, Object> chromeprefs = new HashMap<String, Object>();
            chromeprefs.put("profile.default_content_settingd.popups", 0);
            /*String download= System.getProperty("user.dir");
            chromeprefs.put("download.default_directory", download +"\\xyz");*/
            chromeOptions.setExperimentalOption("prefs", chromeprefs);

            chromeOptions.addArguments("--disable-gpu");
//            chromeOptions.addArguments("headless", "--window-size=1920,1200");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        } else if ("Firefox".equalsIgnoreCase(environmentProperties.getBrowser())) {
            //WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--disable-gpu");
            firefoxOptions.addArguments("headless", "--window-size=1920,1200");
            firefoxOptions.addArguments("--start-maximized");
            driver = new FirefoxDriver(firefoxOptions);
        } else if ("Edge".equalsIgnoreCase(environmentProperties.getBrowser())) {
            //WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();

            HashMap<String, Object> chromeprefs = new HashMap<String, Object>();
            chromeprefs.put("profile.default_content_settingd.popups", 0);
            String download = System.getProperty("user.dir");
            chromeprefs.put("download.default_directory", download + "\\xyz");
            edgeOptions.setExperimentalOption("prefs", chromeprefs);

            edgeOptions.addArguments("--disable-gpu");
//            edgeOptions.addArguments("headless", "--window-size=1920,1200");
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(edgeOptions);
            driver.manage().window().maximize();
        }

        return driver;
    }
}

