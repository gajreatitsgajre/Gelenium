package com.application.core.configuration;

import com.application.core.DependencyInjector;
import com.application.core.PageFactory;
import com.application.core.browser.BrowserProvider;
import com.application.steps.Step;
import com.application.utils.PropertyMap;
import com.google.inject.*;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import cucumber.api.guice.CucumberModules;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import cucumber.runtime.java.guice.InjectorSource;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public class GeleniumConfiguration extends AbstractModule implements InjectorSource, DependencyInjector {

    private Injector injector;
    private PropertyMap properties;

    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(BrowserProvider.class).asEagerSingleton();
        for(Class<?> stepDef: getAllStepDefinitionClasses()){
            bind(stepDef).in(ScenarioScoped.class);
        }
        for(Class<?> stepClass: getAllSteps()){
            bind(stepClass);
        }
        Names.bindProperties(binder(), getProperties());
        bindListener(Matchers.any(), new GelenityPageObjectModelListner());
    }

    @Override
    public Injector getInjector() {
        if(injector == null){
            injector = Guice.createInjector(Stage.PRODUCTION,
                    CucumberModules.SCENARIO, this);
        }
        return injector;
    }

    @Provides
    public PageFactory getPageFactory(){
        return new PageFactory(this);
    }

    @Override
    public <T>  T get(Class<T> obj) {
        return getInjector().getInstance(obj);
    }

    private List<Class<?>> getAllSteps(){
        List<Class<?>> steps = new ArrayList<>();
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.forPackages("com.application.steps").setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
        Reflections reflections = new Reflections(builder);
        Set<Class<?>> stepClasses = reflections.getTypesAnnotatedWith(Step.class);
        for(Class<?> stepClass: stepClasses){
            steps.add(stepClass);
        }
        return steps;
    }

    private List<Class<?>> getAllStepDefinitionClasses(){
        List<Class<?>> stepDefinitions = new ArrayList<>();
        List<Class<? extends Annotation>> cucumberAnnotations = new ArrayList<>();
        cucumberAnnotations.add(And.class);
        cucumberAnnotations.add(Given.class);
        cucumberAnnotations.add(Then.class);
        cucumberAnnotations.add(When.class);
        cucumberAnnotations.add(But.class);
        cucumberAnnotations.add(After.class);
        cucumberAnnotations.add(Before.class);
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.forPackages("com.application.tests").setScanners(new MethodAnnotationsScanner());
        Reflections reflections = new Reflections(builder);
        for(Class<? extends Annotation> annotation: cucumberAnnotations){
            Set<Method> methods = reflections.getMethodsAnnotatedWith(annotation);
            if(methods.size() > 0){
                Class<?> stepDefinitionClass = methods.iterator().next().getDeclaringClass();
                if(!stepDefinitions.contains(stepDefinitionClass)) {
                    stepDefinitions.add(stepDefinitionClass);
                }
            }
        }
        return stepDefinitions;
    }

    private Properties getProperties(){
        if(properties == null){
            properties = new PropertyMap();
        }
        return properties.getProperties();
    }


}
