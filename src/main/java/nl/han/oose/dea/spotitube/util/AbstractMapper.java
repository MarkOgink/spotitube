package nl.han.oose.dea.spotitube.util;
import nl.han.oose.dea.spotitube.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMapper <T>{
    protected static DatabaseProperties databaseProperties;
    protected Logger logger;

    abstract protected String findStatement();
    protected abstract String deleteStatement();
    abstract protected Object doLoad(ResultSet rs) throws SQLException;

    public ArrayList<T> abstractFindAll() {
        ArrayList<T> list = new ArrayList<>();
        PreparedStatement findStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            findStatement = connection.prepareStatement(findStatement());
            ResultSet rs = findStatement.executeQuery();
            while (rs.next()){
                list.add((T) doLoad(rs));
            }
            return list;
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return null;
    }

    public Object abstractFindOne(String id) {
        if(databaseProperties == null) databaseProperties = new DatabaseProperties();
        PreparedStatement findStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setString(1, id);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            return doLoad(rs);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return null;
    }

    public void abstractInsert(String insertStatement){
        PreparedStatement insert;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            insert = connection.prepareStatement(insertStatement);
            insert.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }


    public void executeStatement(){
        PreparedStatement statement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            statement = connection.prepareStatement(deleteStatement());
            statement.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

}
