package ChildCareTech.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ConfigSingleton {

    private static ConfigSingleton instance;
    private static Properties properties;
    private static String filePath;

    static {
        instance = new ConfigSingleton();
        properties = new Properties();
    }

    private ConfigSingleton() { }

    public ConfigSingleton getInstance() { return instance; }

    private static Properties getProperties(String filePath) throws Exception{

        Properties properties = new Properties();
        try
        {
            final FileInputStream in = new FileInputStream(filePath);
            properties.load(in);
            in.close();
        }
        catch (FileNotFoundException fnfEx)
        {
            System.err.println("Could not read properties from file " + filePath);
        }
        catch (IOException ioEx)
        {
            System.err.println(
                    "IOException encountered while reading from " + filePath);
        }
        return properties;

    }

    private static void storeProperties(Properties properties, String filePath) {
        try
        {
            FileOutputStream fos = new FileOutputStream((filePath));
            properties.storeToXML(fos, null);
            fos.close();
        }
        catch (IOException ioEx)
        {
            System.err.println("ERROR trying to store properties in XML!");
        }
    }


}
