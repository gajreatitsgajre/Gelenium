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
import java.util.List;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class WebElementsFieldInitializer {
    @Inject
    private DependencyInjector injector;

    public boolean initializeField(Field field, Object page, SearchContext searchContext){
        Annotations annotations = new Annotations(field);
        By by = annotations.buildBy();
        try {
            WebElementListHandler e = new WebElementListHandler(injector, searchContext, by);
            List<WebElement> element = (List<WebElement>)Proxy.newProxyInstance(WebElement.class.getClassLoader(),
                    new Class[]{List.class, WebElement.class, Locatable.class, SearchContext.class, WrapsElement.class}, e);
            field.setAccessible(true);
            field.set(page, element);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
}
