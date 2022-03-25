package nl.han.oose.dea.spotitube.tracks.playlist;

import nl.han.oose.dea.spotitube.util.AbstractMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class TrackInPlaylistMapper extends AbstractMapper {

    private static final String COLUMNS = "playlistid, trackid";

    @Override
    protected String findStatement() {
        return "SELECT " + COLUMNS +
                " FROM tracksinplaylist";
    }

    @Override
    protected String deleteStatement() {
        return null;
    }

    @Override
    protected TracksInPlaylist doLoad(ResultSet rs) throws SQLException {
        return new TracksInPlaylist(rs.getInt(1), rs.getInt(2));
    }

    public void insert(int playlistid, int trackid) {
        PreparedStatement insertStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            String insertString = "INSERT INTO tracksinplaylist VALUES (?,?)";
            insertStatement = connection.prepareStatement(insertString);
            insertStatement.setInt(1, playlistid);
            insertStatement.setInt(2, trackid);
            insertStatement.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    public ArrayList<TracksInPlaylist> getTracksInPlaylists() {
        return abstractFindAll();
    }

    public void deleteTrack(int playlistid, int trackid) {
        PreparedStatement deleteStatement;
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            String deleteString = "DELETE FROM tracksinplaylist WHERE playlistid=? AND trackid=?";
            deleteStatement = connection.prepareStatement(deleteString);
            deleteStatement.setInt(1, playlistid);
            deleteStatement.setInt(2, trackid);
            deleteStatement.executeUpdate();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }
}
