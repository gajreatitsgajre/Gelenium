package com.amazon.test.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.*;
import org.slf4j.*;

/**
 * Created by Yuvarej on 28/05/2016.
 */
public class HeaderPage extends PageObject{

    private static final Logger LOG = LoggerFactory.getLogger(HeaderPage.class);

    @FindBy(id = "twotabsearchtextbox")
    private WebElementFacade searchBox;

    @FindBy(xpath = "*[@id='nav-search-submit-text']/..")
    private WebElementFacade searchButton;


    /**
     * types into the search box and submits the search
     * @param searchTerm search term to be used for search
     */
    public void typeAndSubmitSearchBox(String searchTerm){
        typeIntoSearchBox(searchTerm);
        submitSearchButton();
    }

    /**
     * type into search box
     * @param searchTerm search term
     */
    private void typeIntoSearchBox(String searchTerm){
        searchBox.type(searchTerm);
    }

    /**
     * submits the search button
     */
    private void submitSearchButton(){
        searchBox.submit();
    }
}
