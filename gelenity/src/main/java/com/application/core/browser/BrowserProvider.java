package com.application.core.browser;

import com.application.core.DependencyFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
@Singleton
public class BrowserProvider implements DependencyFactory<WebDriver>, Provider<WebDriver> {


    @Inject
    @Named("app.browser")
    private String browserName;

    @Override
    public WebDriver get() {
        if(browserName.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("iexplorer")){
            return new InternetExplorerDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            return new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("safari")){
            return new SafariDriver();
        }
        return new FirefoxDriver();
    }
}
