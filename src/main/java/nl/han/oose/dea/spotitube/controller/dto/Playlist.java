package nl.han.oose.dea.spotitube.controller.dto;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private Tracks tracks;

    public Playlist(int id, String name, boolean owner){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = new Tracks(id);
        //tracks.add("nummero2");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
