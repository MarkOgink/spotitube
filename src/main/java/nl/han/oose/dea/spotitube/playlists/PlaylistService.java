package nl.han.oose.dea.spotitube.playlists;

import nl.han.oose.dea.spotitube.playlists.dto.Playlist;
import nl.han.oose.dea.spotitube.playlists.dto.PlaylistResponse;
import nl.han.oose.dea.spotitube.playlists.dto.Playlists;

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
                (new PlaylistResponse(playlist.id,playlist.name,username.equals(playlist.owner), null)));
        return new Playlists(responseArrayList, 0);
    }

    public Playlists addPlaylist(String name) {
        int id = playlists.getPlaylists().stream().mapToInt(PlaylistResponse::getId).max().orElseThrow()+1;
        playlistMapper.addPlaylist(id, name, username);
        playlists.getPlaylists().add(new PlaylistResponse(id, name, true, null));
        return playlists;
    }

    public Playlists removePlaylist(int id) {
        playlists.getPlaylists().removeIf(playlistDTO -> playlistDTO.id==id);
        playlistMapper.deletePlaylist(id);
        return playlists;
    }

    public Playlists editPlaylist(int id, String name) {
        playlists.getPlaylists().stream().filter(playlistResponse -> playlistResponse.id==id).findFirst().orElseThrow().name = name;
        playlistMapper.update(id, name);
        return playlists;
    }
}
