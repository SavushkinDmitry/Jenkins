import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(monochrome = true, glue = { "steps" },
        plugin = { "pretty", "html:target/cucumber-html-reports/"},// Packagename
        features = { "src/test/java/features" } // FolderName
)
public class RunnerTest extends AbstractTestNGCucumberTests {

}
