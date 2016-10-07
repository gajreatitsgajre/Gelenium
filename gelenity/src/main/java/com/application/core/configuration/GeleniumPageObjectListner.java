package com.application.core.configuration;

import com.application.core.PageFactory;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.spi.InjectionListener;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class GeleniumPageObjectListner implements InjectionListener {

    private Provider<Injector> injectorProvider;

    public GeleniumPageObjectListner(Provider<Injector> injectorProvider){
        this.injectorProvider = injectorProvider;
    }

    @Override
    public void afterInjection(Object o) {
        PageFactory factory = this.injectorProvider.get().getInstance(PageFactory.class);
        factory.get(o);
    }
}
