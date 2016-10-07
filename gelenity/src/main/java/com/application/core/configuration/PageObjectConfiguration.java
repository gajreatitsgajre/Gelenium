package com.application.core.configuration;

import com.application.core.DependencyFactory;
import com.application.core.DependencyInjector;
import com.application.core.element.WebElementFieldInitializer;
import com.application.core.element.WebElementsFieldInitializer;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class PageObjectConfiguration extends AbstractModule implements DependencyInjector{

    private Injector injector;
    private DependencyInjector dependencyInjector;

    public PageObjectConfiguration(DependencyInjector dependencyInjector, DependencyFactory factory) {
        this.dependencyInjector = dependencyInjector;
    }

    @Override
    protected void configure() {
        bind(WebElementFieldInitializer.class);
        bind(WebElementsFieldInitializer.class);
        bind(DependencyInjector.class).toInstance(dependencyInjector);
    }

    private Injector getInjector() {
        if(this.injector != null) {
            return this.injector;
        } else {
            this.injector = Guice.createInjector(Stage.PRODUCTION, this);
            return this.injector;
        }
    }

    public <T> T get(Class<T> klass){
        return this.getInjector().getInstance(klass);
    }

    public void injectMembers(Object object) {
        this.getInjector().injectMembers(object);
    }

}
