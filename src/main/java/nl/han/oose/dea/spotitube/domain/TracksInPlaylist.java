package nl.han.oose.dea.spotitube.domain;

public class TracksInPlaylist {
    public int playlistid;
    public int trackid;

    public TracksInPlaylist(int playlistid, int trackid){
        this.playlistid = playlistid;
        this.trackid = trackid;
    }

    public int getTrackid() {
        return trackid;
    }
}
