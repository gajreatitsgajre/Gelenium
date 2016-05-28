package com.amazon.test.utils;

import org.slf4j.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Created by Yuvaraj on 28/05/2016.
 */
public class PropertyMap {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyMap.class);
    private static Properties properties;
    private static boolean loaded =false;

    private static boolean load() throws IOException {
        InputStream stream = PropertyMap.class.getClass().getResourceAsStream("/amazon.properties");
        properties = new Properties();
        properties.load(stream);
        return true;
    }

    /**
     * returns the value assigned to a property
     * @param propertyName property name for which the value should be retrieved from
     * @return value assigned to a property
     */
    public static String getProperty(String propertyName){
        if(!loaded){
            try{
                loaded = load();
            }catch(IOException ex){
                LOG.error(ex.getMessage(),ex);
            }
        }
        String propValue = properties.getProperty(propertyName);
        LOG.info("Value assigned to the property {} is {}", propertyName, propValue);
        return propValue;
    }

}
