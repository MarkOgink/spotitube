package nl.han.oose.dea.spotitube.datasources.dao;

import nl.han.oose.dea.spotitube.datasources.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.resources.LoginRequest;
import nl.han.oose.dea.spotitube.resources.LoginResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDao {
    private DatabaseProperties databaseProperties;
    private Logger logger = Logger.getLogger(getClass().getName());
    private LoginResponse loginResponse;

    public LoginDao(DatabaseProperties databaseProperties){
        this.databaseProperties = databaseProperties;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        try {
            Class.forName(databaseProperties.driverString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error communicating with driver " + databaseProperties.driverString(), e);
        }
        System.out.println(databaseProperties.connectionString());
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            PreparedStatement statement = connection.prepareStatement("SELECT token, name FROM user WHERE username =? AND password = ?");
            statement.setString(1, loginRequest.user);
            statement.setString(2, loginRequest.password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                loginResponse = new LoginResponse(resultSet.getString("token"), resultSet.getString("name"));
            }
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return loginResponse;
    }
}
