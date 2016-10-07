package com.application.core.browser;

import com.application.core.DependencyFactory;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
@Singleton
public class BrowserProvider implements DependencyFactory<WebDriver>, Provider<WebDriver> {

    @Override
    public WebDriver get() {
        return new ChromeDriver();
    }
}
