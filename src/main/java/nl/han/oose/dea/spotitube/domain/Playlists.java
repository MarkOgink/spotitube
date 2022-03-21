package nl.han.oose.dea.spotitube.domain;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<PlaylistResponse> playlists;
    private int length;

    public Playlists(ArrayList<PlaylistResponse> playlists){
        this.playlists = playlists;
    }

    public ArrayList<PlaylistResponse> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistResponse> playlistDTOS) {
        this.playlists = playlists;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
