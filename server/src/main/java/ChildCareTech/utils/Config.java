package ChildCareTech.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Config {

    private Config() { }

    public static Properties getProperties(String filePath) throws Exception{

        Properties properties = new Properties();
        FileInputStream in;
        try
        {
            in = new FileInputStream(filePath);
            properties.loadFromXML(in);
            in.close();
        }
        catch (FileNotFoundException fnfEx)
        {
            System.err.println("Could not read properties from file " + filePath);
            throw new FileNotFoundException();
        }
        catch (IOException ioEx)
        {
            System.err.println(
                    "IOException encountered while reading from " + filePath);
            throw new IOException();
        }
        return properties;

    }

    public static void storeProperties(Properties properties, String filePath) {
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

    public static void setProperties(Properties properties) {

    }
}
