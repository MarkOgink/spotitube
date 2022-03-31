package nl.han.oose.dea.spotitube.playlists;
import nl.han.oose.dea.spotitube.util.AbstractMapper;
import nl.han.oose.dea.spotitube.playlists.dto.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class PlaylistMapper extends AbstractMapper {

    public ArrayList<Playlist> getPlaylists() {
        return abstractFindAll();
    }

    public void deletePlaylist(int id){
        executeStatement();
    }

    public static final String COLUMNS = "id, name, username";

    protected String findStatement() {
        return "SELECT " + COLUMNS +
                " FROM playlist";
    }


    protected String deleteStatement() {
        return "DELETE FROM playlist WHERE id = ?";
    }

    protected String insertStatement(){
        return "INSERT INTO playlist VALUES(?,?,?)";
    }

    protected Playlist doLoad(ResultSet rs) throws SQLException {
        return new Playlist(rs.getInt(1), rs.getString(2), rs.getString(3), null);
    }

    public void addPlaylist(int id, String name, String username) {
        PreparedStatement insertStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            insertStatement = connection.prepareStatement(insertStatement());
            insertStatement.setInt(1, id);
            insertStatement.setString(2, name);
            insertStatement.setString(3, username);
            insertStatement.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    public void update(int id, String name) {
        PreparedStatement updateStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            updateStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?");
            updateStatement.setString(1, name);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }
}
