package nl.han.oose.dea.spotitube.controller.dto;

public class Playlists {
    private Playlist[] playlists;
    private String token;

    public Playlists(){
        Playlist playlist = new Playlist();
        playlists[0] = playlist;
    }
}
