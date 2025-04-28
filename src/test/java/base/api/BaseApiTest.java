package base.api;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class BaseApiTest {
    protected String baseUrl;
    protected String baseRequestBodyPath;
    protected String environment;


    @Parameters({"env"})
    @BeforeClass
    public void setUp(@Optional("dev") String env) {
        log.info("Running tests with environmenta: " + env);

        Properties properties = new Properties();
        String propertiesFilePath = "src/test/resources/config/" + env + ".properties";

        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + propertiesFilePath, e);
        }

        baseUrl = properties.getProperty("base.url");
        baseRequestBodyPath = properties.getProperty("request.bodies.path");
        environment = properties.getProperty("env");
        log.info("Configuration loaded: baseUrl = " + baseUrl + ", requestBodyPath = " + baseRequestBodyPath + ", environment = " + environment);

        validateProperties();
    }

    private void validateProperties() {
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("Missing property: base.url");
        }
        if (baseRequestBodyPath == null || baseRequestBodyPath.isEmpty()) {
            throw new IllegalStateException("Missing property: request.bodies.path");
        }
        if (environment == null || environment.isEmpty()) {
            throw new IllegalStateException("Missing property: env");
        }
    }
}
