package nl.han.oose.dea.spotitube.tracks.dto;

import java.sql.Date;

public class TrackDTO extends Track{
    public TrackDTO(int id, String title, String performer, int duration, String album, int playcount, Date publicationDate, String description, boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        if(publicationDate!=null) this.publicationDate = publicationDate.toString();
        this.description = description;
        this.offlineAvailable = offlineAvailable;
    }
    public int getDuration(){
        return duration;
    }
}
