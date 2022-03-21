package nl.han.oose.dea.spotitube.domain;

public class Playlist {
    public int id;
    public String name;
    public String owner;
    public Tracks tracks;

    public Playlist(int id, String name, String owner, Tracks tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }
}
