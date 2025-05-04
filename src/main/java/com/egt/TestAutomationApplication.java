package com.egt;

import com.egt.configuration.ApiTestConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApiTestConfig.class)
public class TestAutomationApplication {
}
