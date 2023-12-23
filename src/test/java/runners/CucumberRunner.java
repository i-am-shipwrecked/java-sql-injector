package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        stepNotifications = true,
        tags = "@Run",
        features = "src/test/java/features",
        glue = "steps",
        plugin = {
                "pretty", "html:target/serenity-reports/serenity-html-report",
                "json:target/serenity-reports/SerenityTestReport.json",
                "rerun:target/serenity-reports/rerun.txt"}
)
public class CucumberRunner {
}
