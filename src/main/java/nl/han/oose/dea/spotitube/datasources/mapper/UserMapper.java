package nl.han.oose.dea.spotitube.datasources.mapper;
import nl.han.oose.dea.spotitube.domain.User;
import java.sql.*;

public class UserMapper extends AbstractMapper{
    public static final String COLUMNS = " username, user, password";

    protected String findStatement() {
        return "SELECT " + COLUMNS +
                " FROM user" +
                " WHERE username = ?";
    }

    public User find(String username) {
        return (User) abstractFindOne(username);
    }

    protected User doLoad(ResultSet rs) throws SQLException {
        return new User(rs.getString(1), rs.getString(2), rs.getString(3));
    }
}
