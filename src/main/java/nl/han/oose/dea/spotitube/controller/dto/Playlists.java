package nl.han.oose.dea.spotitube.controller.dto;

import java.util.ArrayList;

public class Playlists {
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private int length;

    public Playlists(){
        Playlist playlist1 = new Playlist(1, "Death Metal", true);
        Playlist playlist2 = new Playlist(2, "Pop", false);
        playlists.add(playlist1);
        playlists.add(playlist2);
        length = 500;
    }

//    public void removePlaylistFromPlaylists(Playlists playlists, int id){
//        for (int i = 0; i < playlists.getLength(); i++) {
//            if(playlists.getPlaylists().get(i).getId()==id){
//                playlists.getPlaylists().remove(i);
//            }
//        }
//    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
