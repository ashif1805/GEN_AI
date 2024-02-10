package com.gen.ai.config.driver;


import com.metlife.ace.automation.annotation.LazyAutowired;
import com.metlife.ace.automation.annotation.LazyConfiguration;
import com.metlife.ace.automation.config.appconfig.ACEAppConfig;
import com.metlife.ace.automation.config.appconfig.EnvironmentProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@LazyConfiguration
public class WebDriverWaitConfig {
    @LazyAutowired
    private AppConfig genAppConfig;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait webdriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(this.gebAppConfig.getBrowserTimeOut()));
    }

}
