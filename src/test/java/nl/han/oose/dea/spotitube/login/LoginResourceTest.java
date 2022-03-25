package nl.han.oose.dea.spotitube.login;

import nl.han.oose.dea.spotitube.login.dto.LoginRequest;
import nl.han.oose.dea.spotitube.login.dto.LoginResponse;
import nl.han.oose.dea.spotitube.login.dto.User;
import nl.han.oose.dea.spotitube.login.LoginResource;
import nl.han.oose.dea.spotitube.login.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginResourceTest {
    private LoginResource sut;
    private LoginService mockedLoginService;


    @BeforeEach
    void setUp() {
        this.sut = new LoginResource();
        this.mockedLoginService = Mockito.mock(LoginService.class);
        this.sut.setLoginService(mockedLoginService);
    }

    @Test
    void loginCallsOnLoginService() {
        //arrange
        LoginRequest user = new LoginRequest();
        user.user="meron";
        user.password="password";
        //act
        sut.login(user);
        //assert
        Mockito.verify(mockedLoginService).login(user);
    }

    @Test
    void loginReturnsBadRequestResponse() {
        //arrange
        LoginRequest user = new LoginRequest();
        //act
        var response = sut.login(user);
        //assert
        assertEquals(400,response.getStatus());
    }

    @Test
    void loginReturnsUnauthorizedResponse() {
        //arrange
        LoginRequest user = new LoginRequest();
        user.user="meron";
        user.password="password";
        //act
        var response = sut.login(user);
        //assert
        assertEquals(401,response.getStatus());
    }

    @Test
    void loginReturnsResourceCreatedResponse() {
        //arrange
        LoginRequest user = new LoginRequest();
        user.user="meron";
        user.password="password";
        Mockito.when(mockedLoginService.login(user)).thenReturn(new User(user.user, "Meron Brouwer", user.password));
        //act
        var response = sut.login(user);
        //assert
        assertEquals(201,response.getStatus());
    }

    @Test
    void loginReturnsEntityOfLoginResponse() {
        //arrange
        LoginRequest user = new LoginRequest();
        user.user="meron";
        user.password="password";
        Mockito.when(mockedLoginService.login(user)).thenReturn(new User(user.user, "Meron Brouwer", user.password));
        //act
        var response = sut.login(user);
        //assert
        assertEquals(LoginResponse.class,response.getEntity().getClass());
    }
}
