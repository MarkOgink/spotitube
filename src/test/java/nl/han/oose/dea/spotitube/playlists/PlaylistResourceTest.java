package nl.han.oose.dea.spotitube.playlists;

import nl.han.oose.dea.spotitube.util.Token;
import nl.han.oose.dea.spotitube.playlists.dto.PlaylistRequest;
import nl.han.oose.dea.spotitube.playlists.dto.Playlists;
import nl.han.oose.dea.spotitube.tracks.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

class PlaylistResourceTest {
    private PlaylistResource sut;
    private PlaylistService mockPlaylistService;
    private TrackService mockTrackservice;
    private final String username = "meron";
    private final String token = Token.setToken(username);
    private Playlists playlists;
    private PlaylistRequest playlist;

    @BeforeEach
    void setUp() {
        this.sut = new PlaylistResource();
        this.mockPlaylistService = Mockito.mock(PlaylistService.class);
        this.mockTrackservice = Mockito.mock(TrackService.class);
        this.sut.setPlaylistService(mockPlaylistService);
        this.sut.setTrackService(mockTrackservice);
        this.playlists=new Playlists(new ArrayList<>(), 0);
        this.playlist = new PlaylistRequest();
    }

    @Test
    void testGetAllPlaylists() {
        sut.getAllPlaylists(token);
        Mockito.verify(mockPlaylistService).getPlaylists(username);
    }

    @Test
    void testGetAllPlaylistsInvalidToken() {
        var response = sut.getAllPlaylists("gibberish");
        Assertions.assertEquals(403, response.getStatus());
    }

    @Test
    void addPlaylist() {
        sut.addPlaylist(token, playlist);
        Mockito.verify(mockPlaylistService).addPlaylist(playlist.name);
    }

    @Test
    void addPlaylistInvalidToken() {
        var response = sut.addPlaylist("gibberish", playlist);
        Assertions.assertEquals(403, response.getStatus());
    }

    @Test
    void removePlaylist() {
        sut.removePlaylist(token, 1);
        Mockito.verify(mockPlaylistService).removePlaylist(1);
    }

    @Test
    void removePlaylistInvalidToken() {
        var response = sut.removePlaylist("gibberish", 1);
        Assertions.assertEquals(403, response.getStatus());
    }

    @Test
    void editPlaylist() {
        sut.editPlaylist(token, 1, playlist);
        Mockito.verify(mockPlaylistService).editPlaylist(1,playlist.name);
    }

    @Test
    void editPlaylistInvalidToken() {
        var response = sut.editPlaylist("gibberish", 1, playlist);
        Assertions.assertEquals(403, response.getStatus());
    }
}
