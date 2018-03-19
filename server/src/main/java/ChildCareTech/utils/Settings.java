package ChildCareTech.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Settings {
    private static final String confPath = "./src/resources/config.xml";
    private static final Properties properties;

    static {
        properties = new Properties();
        FileInputStream in;
        try
        {
            in = new FileInputStream(confPath);
            properties.loadFromXML(in);
            in.close();
        }
        catch (FileNotFoundException fnfEx)
        {
            System.err.println("Could not read properties from file " + confPath);
            fnfEx.printStackTrace();
            System.exit(1);
        }
        catch (IOException ioEx)
        {
            System.err.println("IOException encountered while reading from " + confPath);
            ioEx.printStackTrace();
            System.exit(1);
        }
    }

    private Settings() {}

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

}
