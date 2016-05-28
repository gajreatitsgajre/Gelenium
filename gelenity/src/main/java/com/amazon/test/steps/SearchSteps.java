package com.amazon.test.steps;

import com.amazon.test.pages.*;
import net.thucydides.core.annotations.*;
import net.thucydides.core.steps.*;

/**
 * Created by Yuvarej on 28/05/2016.
 */
public class SearchSteps extends ScenarioSteps{

    private SearchResultsPage searchResultsPage;


    /**
     * clicks the first search item from the search results
     */
    @Step
    public void clickSearchItemByIndex(int index){
        searchResultsPage.clickSearchItem(index);
    }

    /**
     * searches an item
     * @param searchTerm
     */
    @Step
    public void searchAnItem(String searchTerm){
        searchResultsPage.typeAndSubmitSearchBox(searchTerm);
    }


}
