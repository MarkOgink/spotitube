package nl.han.oose.dea.spotitube.domain;

import java.util.ArrayList;

public class Tracks {
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private int playlistId;

    public Tracks(int playlistId, ArrayList<Track> tracks){
        this.playlistId = playlistId;
        this.tracks = tracks;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
