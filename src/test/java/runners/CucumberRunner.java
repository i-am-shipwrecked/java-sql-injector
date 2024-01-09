package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        stepNotifications = true,
        tags = "@Run",
        features = "src/test/java/features",
        glue = "steps",
        objectFactory = PicoFactory.class
)
public class CucumberRunner {
}
