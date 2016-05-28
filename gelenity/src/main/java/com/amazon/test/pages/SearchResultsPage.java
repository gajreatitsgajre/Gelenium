package com.amazon.test.pages;

import net.serenitybdd.core.annotations.findby.*;
import net.serenitybdd.core.pages.*;

import java.util.*;

/**
 * Created by Yuvarej on 28/05/2016.
 */
public class SearchResultsPage extends HeaderPage {

     @FindBy(id = "s-results-list-atf")
     WebElementFacade searchResults;

    /**
     * clicks the search item based on the index passed
     * @param index item to be clicked
     */
    public void clickSearchItem(int index){
         List<WebElementFacade> searchResultItems = searchResults.thenFindAll(By.tagName("li"));
         searchResultItems.get(index).click();
    }

}
