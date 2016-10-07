package com.application.core.element;

import com.application.core.DependencyInjector;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class WebElementFieldInitializer {

    @Inject
    private DependencyInjector injector;

    public boolean initializeField(Field field, Object page, SearchContext searchContext){
        Annotations annotations = new Annotations(field);
        By by = annotations.buildBy();
        try {
            WebElementHandler e = new WebElementHandler(injector, searchContext, by);
            WebElement element = (WebElement) Proxy.newProxyInstance(WebElement.class.getClassLoader(),
                    new Class[]{WebElement.class, Locatable.class, SearchContext.class, WrapsElement.class}, e);
            field.setAccessible(true);
            field.set(page, element);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
}
