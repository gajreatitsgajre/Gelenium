package com.application.core.element;


import com.application.pages.Page;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.annotation.Annotation;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class SeleniumWebElementListner implements TypeListener {

    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        if(isPageObject(typeLiteral.getRawType())) {
            Provider<Injector> injector = typeEncounter.getProvider(Injector.class);
            typeEncounter.register(new SeleniumWebElementInjectionListner(injector));
            System.out.println("Hi Listner is called only on page Object s");
        }

    }

    private boolean isPageObject(Class<?> classa){
        Annotation[] annotations = classa.getAnnotations();
        for(Annotation annotation: annotations){
            if(annotation.annotationType() == Page.class){
                return true;
            }
        }
        return false;
    }
}
