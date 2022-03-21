package nl.han.oose.dea.spotitube.domain;

import java.util.ArrayList;
import java.util.List;

public class Tracks {
    private List<TrackDTO> tracks;

    public Tracks(List<TrackDTO> tracks){
        this.tracks = tracks;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
