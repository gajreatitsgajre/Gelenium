package com.amazon.test.pages;

import com.amazon.test.utils.PropertyMap;

/**
 * Created by Yuvarej on 29/05/2016.
 */
public class HomePage extends HeaderPage{

    /**
     * goes to the application home page by launching the browser
     * if the browser is not launched
     */
    public void goToHomePage(){
        String applicationUrl = PropertyMap.getProperty("app.url");
        getDriver().get(applicationUrl);
    }
}
