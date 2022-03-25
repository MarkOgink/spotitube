package nl.han.oose.dea.spotitube.tracks;

import nl.han.oose.dea.spotitube.tracks.playlist.TrackInPlaylistMapper;
import nl.han.oose.dea.spotitube.playlists.dto.PlaylistResponse;
import nl.han.oose.dea.spotitube.playlists.dto.Playlists;
import nl.han.oose.dea.spotitube.tracks.dto.TrackDTO;
import nl.han.oose.dea.spotitube.tracks.dto.TrackRequest;
import nl.han.oose.dea.spotitube.tracks.playlist.TracksInPlaylist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrackServiceTest {
    TrackService sut;
    TrackMapper trackMapper;
    TrackInPlaylistMapper trackInPlaylistMapper;
    TrackDTO trackDTO;
    TracksInPlaylist tracksInPlaylist;
    int id;
    TrackRequest trackRequest;

    @BeforeEach
    void setUp() {
        this.sut = new TrackService();
        this.trackMapper = Mockito.mock(TrackMapper.class);
        this.sut.setTrackMapper(trackMapper);
        this.trackInPlaylistMapper = Mockito.mock(TrackInPlaylistMapper.class);
        this.sut.setTrackInPlaylistMapper(trackInPlaylistMapper);
        this.trackDTO = new TrackDTO(1,"Song for someone","The Frames",350,
                "the cost", 0,null, null,false);
        TrackService.tracks.add(trackDTO);
        this.id = 1;
        trackRequest = new TrackRequest();
        trackRequest.id=1;
    }

    @AfterEach
    void teardown(){
        TrackService.tracksInPlaylist=null;
        TrackService.tracks=null;
    }

    @Test
    void getTracksOutOfPlaylistWithTrackOutOfPlaylist() {
        this.tracksInPlaylist = new TracksInPlaylist(1,2);
        TrackService.tracksInPlaylist.add(tracksInPlaylist);
        var result = sut.getTracksOutOfPlaylist(id);
        assertEquals(trackDTO, result.getTracks().get(0));
    }

    @Test
    void getTracksOutOfPlaylistWithTrackInPlaylist() {
        this.tracksInPlaylist = new TracksInPlaylist(1,1);
        TrackService.tracksInPlaylist.add(tracksInPlaylist);
        var result = sut.getTracksOutOfPlaylist(id);
        assertEquals(0,result.getTracks().size());
    }

    @Test
    void getTracksInPlaylistWithTrackOutInPlaylist() {
        this.tracksInPlaylist = new TracksInPlaylist(1,1);
        TrackService.tracksInPlaylist.add(tracksInPlaylist);
        var result = sut.getTracksInPlaylist(id);
        assertEquals(trackDTO, result.getTracks().get(0));
    }

    @Test
    void getTracksInPlaylistWithTrackOutOfPlaylist() {
        this.tracksInPlaylist = new TracksInPlaylist(2,1);
        TrackService.tracksInPlaylist.add(tracksInPlaylist);
        var result = sut.getTracksInPlaylist(id);
        assertEquals(0,result.getTracks().size());
    }

    @Test
    void addTrackToPlaylistGetsAdded() {
        TrackRequest trackRequest = new TrackRequest();
        trackRequest.id=1;
        var result = sut.addTrackToPlaylist(2,trackRequest);
        assertEquals(trackRequest.id,TrackService.tracksInPlaylist.get(0).trackid);
    }

    @Test
    void deleteTrackFromPlaylistGetsDeleted() {
        sut.addTrackToPlaylist(2,trackRequest);
        var result = sut.deleteTrack(2,id);
        assertEquals(0,result.getTracks().size());
    }

    @Test
    void getLengthWithNoSong() {
        var result = sut.getLength();
        assertEquals(0, result);
    }

   @Test
    void getLengthWithOneSong() {
        sut.addTrackToPlaylist(2,trackRequest);
        var result = sut.getLength();
        assertEquals(350, result);
   }

   @Test
    void addTracksToAllPlaylist() {
        ArrayList<PlaylistResponse> list = new ArrayList<>();
        list.add(new PlaylistResponse(1,"hiphop",true, null));
        sut.addTrackToPlaylist(1,trackRequest);
        Playlists playlists = new Playlists(list,0);
        var result = sut.addTracksToAllPlaylists(playlists);
        assertEquals(trackDTO,result.getPlaylists().get(0).tracks.getTracks().get(0));
   }
}
