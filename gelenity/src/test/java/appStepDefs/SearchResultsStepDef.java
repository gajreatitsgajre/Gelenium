package appStepDefs;

import com.amazon.test.steps.ApplicationSteps;
import com.amazon.test.steps.SearchSteps;
import cucumber.api.java.en.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.slf4j.*;

/**
 * Created by Yuvarej on 28/05/2016.
 */
public class SearchResultsStepDef {

    private static Logger LOG = LoggerFactory.getLogger(SearchResultsStepDef.class);

    @Steps
    SearchSteps searchSteps;

    @Steps
    ApplicationSteps appSteps;

    @Given("^I launch the amazon application$")
    public void iLaunchTheAmazonApplication() throws Throwable {
        appSteps.goToHomePage();
    }

    @Then("^I search the \"([^\"]*)\" in the search box$")
    public void iSearchTheInTheSearchBox(String searchTerm) throws Throwable {
        searchSteps.searchAnItem(searchTerm);
    }

    @And("^I click the first search result$")
    public void iClickTheFirstSearchResult() throws Throwable {
        searchSteps.clickSearchItemByIndex(0);
    }

    @Then("^I display the price of the first laptop$")
    public void iDisplayThePriceOfTheFirstLaptop() throws Throwable {

    }
}
