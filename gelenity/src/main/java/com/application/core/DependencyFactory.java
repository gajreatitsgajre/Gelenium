package com.application.core;

import com.google.inject.Provider;

/**
 * Created by Yuvaraj on 07/10/2016.
 */
public interface DependencyFactory<T> extends Provider<T> {
    T get();
}
