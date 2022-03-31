package nl.han.oose.dea.spotitube.playlists;

import nl.han.oose.dea.spotitube.playlists.dto.Playlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistServiceTest {
    PlaylistService sut;
    PlaylistMapper playlistMapper;
    String username;
    Playlist playlist;
    ArrayList<Playlist> playlists;

    @BeforeEach
    void setUp() {
        this.sut = new PlaylistService();
        this.playlistMapper = Mockito.mock(PlaylistMapper.class);
        this.sut.setPlaylistMapper(playlistMapper);
        this.username = "meron";
        PlaylistService.username = this.username;
        this.playlist = new Playlist(1, "heavy metal", this.username,null);
        this.playlists = new ArrayList<>(List.of(playlist));
    }

    @AfterEach
    void tearDown() {
        PlaylistService.playlists=null;
        PlaylistService.username=null;
    }

    @Test
    void getPlaylistsCallsOnPlaylistMapper() {
        sut.getPlaylists(username);
        Mockito.verify(playlistMapper).getPlaylists();
    }

    @Test
    void mapToPlaylistsReturnsPlaylists(){
        var response = sut.mapToPlaylists(playlists);
        assertEquals(playlist.id, response.getPlaylists().get(0).id);
    }

    @Test
    void addPlaylist() {
        PlaylistService.playlists = sut.mapToPlaylists(playlists);
        var response = sut.addPlaylist("triphop");
        assertEquals("triphop", response.getPlaylists().get(1).name);
    }

    @Test
    void removePlaylist() {
        PlaylistService.playlists = sut.mapToPlaylists(playlists);
        var response = sut.removePlaylist(1);
        assertEquals(PlaylistService.playlists, response);
    }

    @Test
    void editPlaylist() {
        PlaylistService.playlists = sut.mapToPlaylists(playlists);
        var response = sut.editPlaylist(1,"triphop");
        assertEquals("triphop",response.getPlaylists().get(0).name);
    }
}
