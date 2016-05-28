import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/",format = {"pretty","html:target/cucumber.html","json:target/cucumber.json"})
public class DefinitionTestSuite {}