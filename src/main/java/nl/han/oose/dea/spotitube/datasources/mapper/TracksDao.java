package nl.han.oose.dea.spotitube.datasources.mapper;


import nl.han.oose.dea.spotitube.domain.Track;
import nl.han.oose.dea.spotitube.domain.Tracks;
import nl.han.oose.dea.spotitube.datasources.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;

public class TracksDao {
    private DatabaseProperties databaseProperties;
    private Tracks tracks;

    public TracksDao(DatabaseProperties databaseProperties){
        this.databaseProperties = databaseProperties;
        this.tracks = setTracks();
    }


    public Tracks setTracks(){
        ResultSet rsEmps;
        ArrayList<Track> tracklist = new ArrayList<Track>();
        try (Connection connection = DriverManager.getConnection(databaseProperties.connectionString()))
        {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM track");
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
