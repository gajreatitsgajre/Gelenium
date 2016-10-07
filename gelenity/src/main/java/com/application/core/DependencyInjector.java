package com.application.core;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public interface DependencyInjector {
    <T> T get(Class<T> obj);
}
