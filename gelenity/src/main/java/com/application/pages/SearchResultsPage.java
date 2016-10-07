package com.application.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

/**
 * Created by Yuvarej on 28/05/2016.
 */
@Page
public class SearchResultsPage extends HeaderPage {

     @FindBy(id = "s-results-list-atf")
     public WebElement searchResults;

    /**
     * clicks the search item based on the index passed
     * @param index item to be clicked
     */
    public void clickSearchItem(int index){
         List<WebElement> searchResultItems = searchResults
                 .findElements(By.tagName("li"));
         searchResultItems.get(index).click();
    }

}
