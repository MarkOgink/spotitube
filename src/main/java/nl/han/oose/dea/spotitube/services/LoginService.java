package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.datasources.mapper.UserMapper;
import nl.han.oose.dea.spotitube.domain.User;
import nl.han.oose.dea.spotitube.resources.LoginRequest;
import nl.han.oose.dea.spotitube.resources.LoginResponse;
import org.apache.commons.codec.digest.DigestUtils;
import javax.enterprise.inject.Default;
import java.util.UUID;

@Default
public class LoginService {

    public LoginResponse login(LoginRequest request) {
        UserMapper userMapper = new UserMapper();
        User response = userMapper.find(request.user);
        if (DigestUtils.sha256Hex(request.password).equals(response.password)){
            response.token = UUID.randomUUID().toString();
            return new LoginResponse(response.token, response.name);
        }
        else return null;
    }
}
