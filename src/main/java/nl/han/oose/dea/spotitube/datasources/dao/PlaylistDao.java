package nl.han.oose.dea.spotitube.datasources.dao;

import nl.han.oose.dea.spotitube.controller.dto.Playlist;
import nl.han.oose.dea.spotitube.controller.dto.Playlists;
import nl.han.oose.dea.spotitube.controller.dto.Track;
import nl.han.oose.dea.spotitube.datasources.DatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDao {
    private DatabaseConnection connection;
    private Playlists playlists;

    public PlaylistDao(DatabaseConnection connection){
        this.connection = connection;
        this.playlists = setPlaylists();
    }


    public Playlists setPlaylists(){
        ResultSet rsEmps = null;
        Playlists playlists = new Playlists();
        try
        {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT * FROM playlist");
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
