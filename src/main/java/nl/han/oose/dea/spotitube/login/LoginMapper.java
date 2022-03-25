package nl.han.oose.dea.spotitube.login;
import nl.han.oose.dea.spotitube.util.AbstractMapper;
import nl.han.oose.dea.spotitube.login.dto.User;

import javax.enterprise.inject.Default;
import java.sql.*;

@Default
public class LoginMapper extends AbstractMapper {
    public static final String COLUMNS = " username, user, password";

    protected String findStatement() {
        return "SELECT " + COLUMNS +
                " FROM user" +
                " WHERE username = ?";
    }

    @Override
    protected String deleteStatement() {
        return null;
    }

    public User find(String username) {
        return (User) abstractFindOne(username);
    }

    protected User doLoad(ResultSet rs) throws SQLException {
        return new User(rs.getString(1), rs.getString(2), rs.getString(3));
    }
}
