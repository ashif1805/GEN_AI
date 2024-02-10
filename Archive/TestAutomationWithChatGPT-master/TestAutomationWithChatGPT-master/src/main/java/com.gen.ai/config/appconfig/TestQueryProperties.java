package com.gen.ai.config.appconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "genai")
@PropertySource(value = "classpath:testfile/TestQuery.properties")
@Data
public class TestQueryProperties {
    private Map<String, String> querySql;
}
