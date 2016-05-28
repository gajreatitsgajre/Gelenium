package com.amazon.test.steps;

import com.amazon.test.pages.*;
import net.thucydides.core.annotations.*;
import net.thucydides.core.steps.*;

/**
 * Created by Yuvarej on 29/05/2016.
 */
public class ApplicationSteps extends ScenarioSteps{

    HomePage homePage;

    /**
     * goes to the application home page by launching the browser
     * if the browser is not launched
     */
    @Step
    public void goToHomePage(){
        homePage.goToHomePage();
    }

}
