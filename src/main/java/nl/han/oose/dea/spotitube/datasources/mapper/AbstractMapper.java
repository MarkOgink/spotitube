package nl.han.oose.dea.spotitube.datasources.mapper;
import nl.han.oose.dea.spotitube.datasources.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMapper <T>{
    static DatabaseProperties databaseProperties;
    protected Logger logger;

    abstract protected String findStatement();
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

    public ArrayList<T> abstractFindMany(int id){
        ArrayList<T> list = new ArrayList<>();
        PreparedStatement findStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setInt(1,id);
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

    public int abstractDelete(int id, String deleteString){
        PreparedStatement deleteStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            deleteStatement = connection.prepareStatement(deleteString);
            deleteStatement.setInt(1, id);
            return deleteStatement.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return 0;
    }

}
