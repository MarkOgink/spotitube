package nl.han.oose.dea.spotitube.util;


import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String connectionString()
    {
        return properties.getProperty("databaseurl");
    }

}
