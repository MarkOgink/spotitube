package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.datasources.mapper.PlaylistMapper;
import nl.han.oose.dea.spotitube.domain.*;

import java.util.ArrayList;

public class PlaylistService {
    PlaylistMapper playlistMapper = new PlaylistMapper();
    static Playlists playlists;
    static String username;

    public Playlists getPlaylists(String username) {
        PlaylistService.username = username;
        ArrayList<Playlist> list = playlistMapper.getPlaylists();
        playlists = mapToPlaylists(list);
        return playlists;
    }

    private Playlists mapToPlaylists(ArrayList<Playlist> list) {
        ArrayList<PlaylistResponse> responseArrayList = new ArrayList<>();
        list.forEach(playlist -> responseArrayList.add
                (new PlaylistResponse(playlist.id,playlist.name,username.equals(playlist.owner),null)));
        return new Playlists(responseArrayList);
    }

    public Playlists addPlaylist(String name) {
        int id = playlists.getPlaylists().stream().mapToInt(PlaylistResponse::getId).max().orElseThrow()+1;
        playlistMapper.addPlaylist(id, name, username);
        playlists.getPlaylists().add(new PlaylistResponse(id, name, true, null));
        return playlists;
    }

    public Playlists removePlaylist(int id) {
        playlistMapper.deletePlaylist(id);
        playlists.getPlaylists().removeIf(playlistDTO -> playlistDTO.id==id);
        return playlists;
    }

    public Playlists editPlaylist(int id, String name) {
        if(playlists.getPlaylists().stream().noneMatch(playlistResponse -> playlistResponse.name.equals(name))){
            playlistMapper.update(id, name);
            playlists.getPlaylists().get(id).name = name;
        }
        return playlists;
    }
}
