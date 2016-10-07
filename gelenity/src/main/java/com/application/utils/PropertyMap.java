package com.application.utils;

import java.io.*;
import java.util.*;

/**
 * Created by Yuvaraj on 28/05/2016.
 */
public class PropertyMap {

    public Properties getProperties(){
        try{
            InputStream stream = this.getClass().getResourceAsStream("/application.properties");
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        }
        catch (Exception ex){
           ex.printStackTrace();
        }
        return null;
    }

}
