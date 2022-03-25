package nl.han.oose.dea.spotitube.login;

import nl.han.oose.dea.spotitube.login.dto.LoginRequest;
import nl.han.oose.dea.spotitube.login.dto.User;
import org.apache.commons.codec.digest.DigestUtils;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class LoginService {
    LoginMapper loginMapper;

    @Inject
    public void setUserMapper(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }

    public User login(LoginRequest request) {
        User response = loginMapper.find(request.user);
        if (response!=null&&DigestUtils.sha256Hex(request.password).equals(response.password)){
            return response;
        }
        else return null;
    }
}
