package com.galenframework.java.sample.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.galenframework.specs.page.Locator;

/**
 * Created by biswajip on 3/3/18.
 */
public class Locators {

    private static Map<String, Locator> pageLocators = new HashMap<>();
    private static Map<String, String> domOobjectProperties = new HashMap<>();

    public static Map<String, Locator> getPageLocators() throws IOException{

        Map<String, String> locatorMap = getLocatorProperties();
        for (Map.Entry<String, String> locatorProp : locatorMap.entrySet()){
            pageLocators.put(locatorProp.getKey(), Locator.css(locatorProp.getValue()));
        }

        return pageLocators;
    }

    public static Map<String, String> getLocatorProperties() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream(Config.PROPERTIES_PATH));

        for (Object key: properties.keySet()){
            domOobjectProperties.put((String)key, properties.getProperty((String)key));
        }
        return domOobjectProperties;
    }

    public static String getLocatorProperty(String locatorKey) throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream(Config.PROPERTIES_PATH));

        //TODO - write the below code in java8
        for (Map.Entry<Object, Object> entry: properties.entrySet()){
            if (locatorKey.equalsIgnoreCase((String) entry.getKey())){
                System.out.println("The locator: " + entry.getValue());
                return (String) entry.getValue();
            }
        }

        return null;
    }
}
