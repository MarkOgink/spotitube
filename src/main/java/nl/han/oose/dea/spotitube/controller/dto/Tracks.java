package nl.han.oose.dea.spotitube.controller.dto;

import java.util.ArrayList;

public class Tracks {
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private int playlistId;

    public Tracks(int playlistId){
        this.playlistId = playlistId;
        this.tracks.add(new Track(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew", 0, null, null, false));
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
