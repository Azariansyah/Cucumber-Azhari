package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"StepDefinitions"},
            plugin = {"pretty",                                   // Pretty console output
                    "html:target/cucumber-report.html",          // HTML report
                    "json:target/cucumber-report.json",          // JSON report
                    "junit:target/cucumber-report.xml"           // JUnit XML report
            }
    )
    public class TestRunner {
    }

