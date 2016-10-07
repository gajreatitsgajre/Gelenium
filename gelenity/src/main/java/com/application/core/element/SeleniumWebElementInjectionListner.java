package com.application.core.element;

import com.application.core.PageFactory;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class SeleniumWebElementInjectionListner implements InjectionListener {

    private Provider<Injector> injectorProvider;

    public SeleniumWebElementInjectionListner(Provider<Injector> injectorProvider){
        this.injectorProvider = injectorProvider;
    }

    public void afterInjection(Object o) {
        PageFactory factory = injectorProvider.get().getInstance(PageFactory.class);
        factory.get(o);
    }
}
