package com.gen.ai.config.appconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "genai.app")
@Data
public class EnvironmentProperties {
    private String browser;
    private String region;
    private String url;
    private String testDataSheet;
    private String chromeDriverPath;
    private boolean enableTestEvidence;
    private Map<String, Map<String, String>> service;
    private String userId;
    private String userPass;
    private String uploadableFilePath;
}
