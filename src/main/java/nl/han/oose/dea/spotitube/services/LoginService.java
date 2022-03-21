package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.datasources.mapper.UserMapper;
import nl.han.oose.dea.spotitube.domain.User;
import nl.han.oose.dea.spotitube.domain.LoginRequest;
import org.apache.commons.codec.digest.DigestUtils;
import javax.enterprise.inject.Default;

@Default
public class LoginService {
    UserMapper userMapper = new UserMapper();

    public User login(LoginRequest request) {
        User response = userMapper.find(request.user);
        if (DigestUtils.sha256Hex(request.password).equals(response.password)){
            return response;
        }
        else return null;
    }
}
