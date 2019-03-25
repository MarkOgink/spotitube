package nl.han.oose.dea.spotitube.datasources.dao;


import nl.han.oose.dea.spotitube.controller.dto.Track;
import nl.han.oose.dea.spotitube.controller.dto.Tracks;
import nl.han.oose.dea.spotitube.datasources.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class TracksDao {
    private DatabaseConnection connection;
    private Tracks tracks;

    public TracksDao(DatabaseConnection connection){
        this.connection = connection;
        this.tracks = setTracks();
    }


    public Tracks setTracks(){
        ResultSet rsEmps = null;
        ArrayList<Track> tracklist = new ArrayList<Track>();
        try
        {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT * FROM track");
            rsEmps = st.executeQuery();
            while (rsEmps.next())
            {
                int id = rsEmps.getInt("id");
                String title = rsEmps.getString("title");
                String performer = rsEmps.getString("performer");
                int duration = rsEmps.getInt("duration");
                String album = rsEmps.getString("album");
                int playcount = rsEmps.getInt("playcount");
                Date date = rsEmps.getDate("publication_date");
                String descritpion = rsEmps.getString("description");
                boolean offlineAvailable = rsEmps.getBoolean("offlineAvailable");
                System.out.println(title);
                tracklist.add(new Track(id, title, performer, duration, album, playcount, date, descritpion, offlineAvailable));
            }
            return new Tracks(3, tracklist);
        }
        catch (SQLException e)
        {
            System.out.println("Error executing query: " + e);
        }
        return null;
    }
}
