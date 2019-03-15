package nl.han.oose.dea.spotitube.controller.dto;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<Playlist> playlists;
    private String token;

    public Playlists(String token){
        this.token = token;
        Playlist playlist = new Playlist();
        playlists.add(playlist);
    }
}
