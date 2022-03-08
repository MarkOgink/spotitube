package nl.han.oose.dea.spotitube.datasources.mapper;
import nl.han.oose.dea.spotitube.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends AbstractMapper {
    public static final String COLUMNS = " username, name, password";

    protected String findStatement() {
        return "SELECT " + COLUMNS +
                " FROM user" +
                " WHERE username = ?";
    }

    public User find(String username) {
        return (User) abstractFind(username);
    }

    protected Object doLoad(String id, ResultSet rs) throws SQLException {
        String username = rs.getString(1);
        String name = rs.getString(2);
        String password = rs.getString(3);
        return new User(username, name, password, null);
    }
}
