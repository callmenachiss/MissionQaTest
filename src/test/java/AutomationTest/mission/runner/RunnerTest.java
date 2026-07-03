package AutomationTest.mission.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {
                "src/test/java/API-Test.feature",
                "src/test/java/UI-Test.feature"
        },
        glue = {
                "AutomationTest.mission.hooks",
                "AutomationTest.mission.steps"
        },
        monochrome = true,
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/report.html",
                "json:test-output/cucumber-reports/CucumberTestReport.json",
                "rerun:test-output/cucumber-reports/rerun.txt"
        }
)
public class RunnerTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}