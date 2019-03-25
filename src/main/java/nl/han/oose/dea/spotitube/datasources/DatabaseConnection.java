package nl.han.oose.dea.spotitube.datasources;

import nl.han.oose.dea.spotitube.controller.dto.Track;
import nl.han.oose.dea.spotitube.controller.dto.Tracks;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class DatabaseConnection {

    private Connection connection = null;

    public DatabaseConnection(){
        connectToDatabase();
    }
    public Connection getConnection() {
        return connection;
    }

    public void connectToDatabase() {

        try {
            var properties = new Properties();
            properties.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("database.properties")));
            Class.forName(properties.getProperty("driver"));

            connection = DriverManager.getConnection(properties.getProperty("connectionString"));
            } catch (SQLException e) {
                System.out.println("Error connecting to a database: " + e);
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
    }


}
