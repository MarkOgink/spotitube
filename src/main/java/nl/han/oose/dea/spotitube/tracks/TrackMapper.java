package nl.han.oose.dea.spotitube.tracks;
import nl.han.oose.dea.spotitube.util.AbstractMapper;
import nl.han.oose.dea.spotitube.tracks.dto.TrackDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackMapper extends AbstractMapper {
    public static final String COLUMNS = "id, title, performer, duration, album, playcount, publicationDate," +
            " description, offlineAvailable";

    @Override
    protected String findStatement() {
        return "SELECT " + COLUMNS +
                " FROM track";
    }

    @Override
    protected String deleteStatement() {
        return null;
    }

    @Override
    protected Object doLoad(ResultSet rs) throws SQLException {
        return new TrackDTO(rs.getInt(1),rs.getString(2),rs.getString(3),
                rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getDate(7),
                rs.getString(8), rs.getBoolean(9));
    }

    public ArrayList<TrackDTO> getTracks() {
        return abstractFindAll();
    }
}
