package nl.han.oose.dea.spotitube.playlists.dto;

import nl.han.oose.dea.spotitube.tracks.dto.Tracks;

public class PlaylistResponse{
    public int id;
    public String name;
    public boolean owner;
    public Tracks tracks;

    public PlaylistResponse(int id, String name, boolean owner, Tracks tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }
}
