package com.gen.ai.config.webelement;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "ace.page.base.element")
@PropertySource(value = "classpath:pageelements/BasePageElements.properties")
@Data
public class BasePageElement {
    private String searchFilter;
    private String searchLocator;
    private String listSelectorLocator;

}
