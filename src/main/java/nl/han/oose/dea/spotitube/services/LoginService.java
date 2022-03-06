package nl.han.oose.dea.spotitube.services;

import org.apache.commons.codec.*;
import nl.han.oose.dea.spotitube.datasources.mapper.UserMapper;
import nl.han.oose.dea.spotitube.domain.User;
import nl.han.oose.dea.spotitube.resources.LoginRequest;

import javax.enterprise.inject.Default;
import java.util.UUID;

@Default
public class LoginService {


    public User login(LoginRequest request) {
        //DigestUtils.sha256(request.password);
        UserMapper userMapper = new UserMapper();
        User response = userMapper.find(request.user);
        if ((request.password).equals(response.password)){
            if(response.token == null){
                response.token = UUID.randomUUID().toString();
            }
            return response;
        }
        else return null;
    }
}
