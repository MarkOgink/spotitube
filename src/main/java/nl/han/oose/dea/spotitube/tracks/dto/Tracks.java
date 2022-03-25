package nl.han.oose.dea.spotitube.tracks.dto;

import java.util.ArrayList;

public class Tracks {
    private ArrayList<TrackDTO> tracks;

    public Tracks(ArrayList<TrackDTO> tracks){
        this.tracks = tracks;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
