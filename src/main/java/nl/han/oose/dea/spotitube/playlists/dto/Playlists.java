package nl.han.oose.dea.spotitube.playlists.dto;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<PlaylistResponse> playlists;
    private int length;

    public Playlists(ArrayList<PlaylistResponse> playlists, int length){
        this.playlists = playlists;
        this.length = length;
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
