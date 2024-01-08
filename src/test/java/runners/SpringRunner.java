package runners;

import config.SpringRunnerConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-reports"},
        objectFactory = PicoFactory.class
)
@SpringBootTest
@ContextConfiguration(classes = SpringRunnerConfig.class)

public class SpringRunner {
}
