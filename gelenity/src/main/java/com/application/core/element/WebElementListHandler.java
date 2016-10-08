package com.application.core.element;

import com.application.core.DependencyInjector;
import org.openqa.selenium.*;
import java.lang.reflect.*;
import java.util.List;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class WebElementListHandler implements InvocationHandler {

    private DependencyInjector driver;
    private  SearchContext searchContext;
    private By byObj;
    private List<WebElement> elements;

    public WebElementListHandler(DependencyInjector injector, SearchContext searchContext, By by){
        this.driver = injector;
        this.searchContext = searchContext;
        this.byObj = by;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<WebElement> element = this.elements;
        if(element == null){
            element = this.searchContext.findElements(byObj);
        }
        return method.invoke(element, args);
    }
}
