package com.genaidemo.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "demo.app")
@PropertySource(value="classpath:application.properties")
@Data
public class AppConfig {
    private String browser;
    private int browserTimeOut;
    private String region;
    private String url;
    private String testDataSheet;
    private String chromeDriverPath;
    private String testEvidenceFolder;
}