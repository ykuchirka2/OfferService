package edu.ykuchirka.Integration.Support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yura on 31.03.2017.
 */
public class PropertiesManager {

    private final Properties configProp = new Properties();

    private PropertiesManager()
    {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder
    {
        private static final PropertiesManager INSTANCE = new PropertiesManager();
    }

    public static PropertiesManager getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key){
        return configProp.getProperty(key);
    }
}
