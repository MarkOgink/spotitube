package nl.han.oose.dea.spotitube.datasources.dao;

import nl.han.oose.dea.spotitube.domain.Playlist;
import nl.han.oose.dea.spotitube.domain.Playlists;
import nl.han.oose.dea.spotitube.datasources.util.DatabaseProperties;

import java.sql.*;

public class PlaylistDao {
    private DatabaseProperties databaseProperties;
    private Playlists playlists;

    public PlaylistDao(DatabaseProperties databaseProperties){
        this.databaseProperties = databaseProperties;
        this.playlists = setPlaylists();
    }


    public Playlists setPlaylists(){
        ResultSet rsEmps;
        Playlists playlists = new Playlists();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString()))
        {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM playlist");
            rsEmps = st.executeQuery();

            while (rsEmps.next())
            {
                int id = rsEmps.getInt("id");
                String name = rsEmps.getString("name");
                boolean owner = rsEmps.getBoolean("owner");
                playlists.getPlaylists().add(new Playlist(id, name, owner, null));

            }
            return playlists;
        }
        catch (SQLException e)
        {
            System.out.println("Error executing query: " + e);
        }
        return null;
    }

    public Playlists getPlaylists() {
        return playlists;
    }
}
