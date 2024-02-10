package com.gen.ai.config.appconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "genai.app")
@PropertySource(value = "classpath:application.properties")
@Data
public class ACEAppConfig {
    private String testEvidenceFolder;
    private int browserTimeOut;
    public String url;
    public String testEvidenceFolder;

}
