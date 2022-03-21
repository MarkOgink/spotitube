package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.datasources.mapper.TrackInPlaylistMapper;
import nl.han.oose.dea.spotitube.datasources.mapper.TrackMapper;
import nl.han.oose.dea.spotitube.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrackService {
    TrackMapper trackMapper;
    TrackInPlaylistMapper trackInPlaylistMapper;
    public static List<TrackDTO> tracks;
    public static ArrayList<TracksInPlaylist> tracksInPlaylist;

    public TrackService(){
        trackMapper = new TrackMapper();
        trackInPlaylistMapper = new TrackInPlaylistMapper();
        tracks = trackMapper.getTracks();
        tracksInPlaylist = trackInPlaylistMapper.getTracksInPlaylists();
    }

    public Tracks getTracksOutOfPlaylist(int forPlaylist) {
        //Check if playlist is empty
        if(tracksInPlaylist.stream().noneMatch(playlist -> playlist.playlistid==forPlaylist)){
            return new Tracks(tracks);
        }
        else return new Tracks(tracks.stream().filter(trackDTO -> !trackInPlaylist(trackDTO,forPlaylist)).collect(Collectors.toList()));
    }

    private boolean trackInPlaylist(TrackDTO trackDTO, int forPlaylist) {
        return tracksInPlaylist.stream().anyMatch(playlist -> playlist.playlistid==forPlaylist&&playlist.trackid==trackDTO.id);
    }

    public Tracks getTracksInPlaylist(int playlistid){
        return new Tracks(tracks.stream().filter(trackDTO ->
                tracksInPlaylist.stream().anyMatch(playlist ->
                        playlist.playlistid==playlistid&&playlist.trackid==trackDTO.id)).collect(Collectors.toList()));
    }

    public Tracks addTrackToPlaylist(int playlistid, TrackRequest track) {
        //check if track exists
        if(tracks.stream().anyMatch(trackDTO -> trackDTO.id == track.id&&trackDTO.offlineAvailable==track.offlineAvailable)){
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
}
