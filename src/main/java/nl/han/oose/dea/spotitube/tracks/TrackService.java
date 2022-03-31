package nl.han.oose.dea.spotitube.tracks;

import nl.han.oose.dea.spotitube.playlists.dto.Playlists;
import nl.han.oose.dea.spotitube.tracks.dto.TrackDTO;
import nl.han.oose.dea.spotitube.tracks.dto.TrackRequest;
import nl.han.oose.dea.spotitube.tracks.dto.Tracks;
import nl.han.oose.dea.spotitube.tracks.playlist.TracksInPlaylist;
import nl.han.oose.dea.spotitube.tracks.playlist.TrackInPlaylistMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TrackService {
    TrackMapper trackMapper;
    TrackInPlaylistMapper trackInPlaylistMapper;
    public static ArrayList<TrackDTO> tracks;
    public static ArrayList<TracksInPlaylist> tracksInPlaylist;

    @Inject
    public void setTrackMapper(TrackMapper trackMapper){
        this.trackMapper = trackMapper;
        tracks = trackMapper.getTracks();
    }

    @Inject
    public void setTrackInPlaylistMapper(TrackInPlaylistMapper trackInPlaylistMapper){
        this.trackInPlaylistMapper = trackInPlaylistMapper;
        tracksInPlaylist = trackInPlaylistMapper.getTracksInPlaylists();
    }

    public Tracks getTracksOutOfPlaylist(int forPlaylist) {
        return new Tracks(tracks.stream().filter(trackDTO ->
                tracksInPlaylist.stream().noneMatch(playlist ->
                        playlist.playlistid==forPlaylist&&playlist.trackid==trackDTO.id))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public Tracks getTracksInPlaylist(int playlistid){
        return new Tracks(tracks.stream().filter(trackDTO ->
                tracksInPlaylist.stream().anyMatch(playlist ->
                        playlist.playlistid==playlistid&&playlist.trackid==trackDTO.id))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public Tracks addTrackToPlaylist(int playlistid, TrackRequest track) {
        if(tracks.stream().anyMatch(trackDTO -> trackDTO.id == track.id)){
            trackInPlaylistMapper.insert(playlistid, track.id);
            tracksInPlaylist.add(new TracksInPlaylist(playlistid, track.id));
        }
        return getTracksInPlaylist(playlistid);
    }

    public Tracks deleteTrack(int playlistid, int trackid) {
        trackInPlaylistMapper.deleteTrack(playlistid, trackid);
        tracksInPlaylist.removeIf(playlist -> playlist.playlistid==playlistid&&playlist.trackid==trackid);
        return getTracksInPlaylist(playlistid);
    }

    public int getLength() {
        return tracksInPlaylist.stream().mapToInt(track -> tracks.stream().filter(trackDTO -> trackDTO.id == track.trackid).mapToInt(trackDTO -> trackDTO.duration).sum()).sum();
    }

    public Playlists addTracksToAllPlaylists(Playlists playlists) {
        playlists.getPlaylists().forEach(playlistResponse -> playlistResponse.tracks = getTracksInPlaylist(playlistResponse.id));
        playlists.setLength(getLength());
        return playlists;
    }
}
