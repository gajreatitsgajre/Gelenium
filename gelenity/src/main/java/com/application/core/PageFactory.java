package com.application.core;

import com.application.core.browser.BrowserProvider;
import com.application.core.configuration.PageObjectConfiguration;
import com.application.core.element.WebElementFieldInitializer;
import com.application.core.element.WebElementsFieldInitializer;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class PageFactory implements WebDriverEventListener, DependencyFactory<PageFactory> {

    private PageObjectConfiguration configuration;
    private EventFiringWebDriver eventFiringWebDriver;

    @Inject
    private DependencyInjector dependencyInjector;

    public PageFactory(DependencyInjector injector){
        this.configuration = new PageObjectConfiguration(injector, new BrowserProvider());
        this.configuration.injectMembers(this);
    }


    public <T> T get(T page){
        Field[] fields = page.getClass().getFields();
        for(Field field: fields){
            WebElementFieldInitializer fieldInitializer = new WebElementFieldInitializer();
            WebElementsFieldInitializer webElementsFieldInitializer = new WebElementsFieldInitializer();
            this.configuration.injectMembers(fieldInitializer);
            this.configuration.injectMembers(webElementsFieldInitializer);
            if(field.getGenericType().equals(WebElement.class)) {
                fieldInitializer.initializeField(field, page, getDriver());
            }
            else if((field.getGenericType() instanceof ParameterizedType)) {
                if (((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]
                        .equals(WebElement.class)) {
                    webElementsFieldInitializer.initializeField(field, page, getDriver());
                }
            }
        }
        return (T)page;
    }

    private WebDriver getDriver() {
        if(this.eventFiringWebDriver != null) {
            return this.eventFiringWebDriver;
        } else {
            this.eventFiringWebDriver = new EventFiringWebDriver((WebDriver)this.dependencyInjector.get(WebDriver.class));
            this.eventFiringWebDriver.register(this);
            return this.eventFiringWebDriver;
        }
    }

    @Override
    public PageFactory get() {
        return this;
    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }
}
