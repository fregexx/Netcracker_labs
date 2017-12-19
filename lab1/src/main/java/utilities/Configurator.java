package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sorters.IPersonListSorter;
import sorters.impls.PersonBubbleSorter;

import java.io.*;
import java.util.Properties;

public class Configurator {

    public static Configurator configurator = new Configurator();

    private static final Logger LOGGER = LogManager.getLogger(Configurator.class);
    private static final String PATH = "lab1/src/main/resources/config.properties";
    private static IPersonListSorter sorter;
    private static Properties properties;

    private Configurator() {
    }

    public static Configurator getInstance() {
        return configurator;
    }


    public IPersonListSorter getSorter() {
        properties = new Properties();
        String sortProperty = "";
        try {
            properties.load(new FileInputStream(new File(PATH)));
            sortProperty = properties.getProperty("SORTER");
            Class name = Class.forName("sorters.impls." + sortProperty + "Sorter");
            return this.sorter = (IPersonListSorter) name.newInstance();

        } catch (FileNotFoundException e) {
            LOGGER.error("Configuration file config.properties not found in " + PATH);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error("Class " + "sorters.impls." + sortProperty + "Sorter not found");
            e.printStackTrace();
        }
        return new PersonBubbleSorter();
    }
}