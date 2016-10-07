package com.application.core.configuration;

import com.application.pages.Page;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.annotation.Annotation;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class GelenityPageObjectModelListner implements TypeListener {

    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        if(isPageObject(typeLiteral.getRawType())){
            typeEncounter.register(new GeleniumPageObjectListner(typeEncounter.getProvider(Injector.class)));
        }
    }

    private boolean isPageObject(Class<?> page){
        Annotation[] annotations = page.getDeclaredAnnotations();
        if(annotations.length > 0) {
            Annotation annotation = page.getDeclaredAnnotations()[0];
            return annotation.annotationType().equals(Page.class);
        }
        return false;
    }
}
