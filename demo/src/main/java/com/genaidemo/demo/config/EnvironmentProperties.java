package com.genaidemo.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "demo.app")
@Data
public class EnvironmentProperties {
    private String userId;
    private String password;
    private String testDataSheet;
    private boolean enableTestEvidence;
    private Map<String ,Map<String,String>> service;
}
