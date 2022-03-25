package nl.han.oose.dea.spotitube.login;

import nl.han.oose.dea.spotitube.login.LoginMapper;
import nl.han.oose.dea.spotitube.login.dto.LoginRequest;
import nl.han.oose.dea.spotitube.login.dto.User;
import nl.han.oose.dea.spotitube.login.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
    private LoginService sut;
    private LoginMapper mockLoginMapper;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        this.sut = new LoginService();
        this.mockLoginMapper = Mockito.mock(LoginMapper.class);
        this.sut.setUserMapper(mockLoginMapper);
        this.loginRequest = new LoginRequest();
        this.loginRequest.user="meron";
        this.loginRequest.password="password";
    }

    @Test
    void loginCallsUserMapper() {
        sut.login(loginRequest);
        Mockito.verify(mockLoginMapper).find(loginRequest.user);
    }

    @Test
    void loginSuccessful(){
        User user = new User("meron", "Meron Brouwer", DigestUtils.sha256Hex("password"));
        Mockito.when(sut.login(loginRequest)).thenReturn(user);
        var response = sut.login(loginRequest);
        Assertions.assertEquals(user,response);
    }

    @Test
    void loginFail(){
        User user = new User("meron", "Meron Brouwer", DigestUtils.sha256Hex("wrongpassword"));
        Mockito.when(sut.login(loginRequest)).thenReturn(user);
        var response = sut.login(loginRequest);
        assertNull(response);
    }
}
