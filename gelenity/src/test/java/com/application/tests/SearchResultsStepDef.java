package com.application.tests;

import com.application.steps.ApplicationSteps;
import com.application.steps.SearchSteps;
import com.google.inject.Inject;
import cucumber.api.java.en.*;


/**
 * Created by Yuvarej on 28/05/2016.
 */
public class SearchResultsStepDef {

    @Inject
    SearchSteps searchSteps;

    @Inject
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
