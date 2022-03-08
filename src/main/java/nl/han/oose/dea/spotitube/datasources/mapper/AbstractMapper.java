package nl.han.oose.dea.spotitube.datasources.mapper;
import nl.han.oose.dea.spotitube.datasources.util.DatabaseProperties;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMapper {
    DatabaseProperties databaseProperties;
    protected Map loadedMap = new HashMap();
    private Logger logger;

    abstract protected String findStatement();

    public Object abstractFind(String id) {
        Object result = loadedMap.get(id);
        if (result != null) return result;
        databaseProperties = new DatabaseProperties();
        PreparedStatement findStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            findStatement = connection.prepareStatement(findStatement());
            findStatement.setString(1, id);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            result = load(rs);
            return result;
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return null;
    }

    protected Object load(ResultSet rs) throws SQLException {
        String token = rs.getString(1);
        if (loadedMap.containsKey(token)) return loadedMap.get(token);
        Object result = doLoad(token, rs);
        loadedMap.put(token, result);
        return result;
    }

    abstract protected Object doLoad(String token, ResultSet rs) throws SQLException;

}
