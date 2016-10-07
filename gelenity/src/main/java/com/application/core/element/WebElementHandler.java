package com.application.core.element;


import com.application.core.DependencyInjector;
import org.openqa.selenium.*;
import java.lang.reflect.*;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class WebElementHandler implements InvocationHandler{

    private DependencyInjector driver;
    private SearchContext searchContext;
    private By byObj;
    private WebElement element;

    public WebElementHandler(DependencyInjector injector, SearchContext searchContext, By by) {
        this.driver = injector;
        this.searchContext = searchContext;
        this.byObj = by;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object element = this.element;
        if(element == null){
            element = this.searchContext.findElement(byObj);
        }
        return method.invoke(element, args);
    }
}
