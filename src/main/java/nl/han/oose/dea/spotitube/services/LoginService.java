package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.datasources.dao.LoginDao;
import nl.han.oose.dea.spotitube.datasources.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.resources.LoginRequest;
import nl.han.oose.dea.spotitube.resources.LoginResponse;

import javax.enterprise.inject.Default;
import javax.ws.rs.core.Response;

@Default
public class LoginService implements ILoginService{
    private LoginResponse loginResponse;
    @Override
    public Response login(LoginRequest user) {
        LoginDao loginDao = new LoginDao(new DatabaseProperties());
        loginResponse = loginDao.authenticate(user);
        if(loginResponse != null){
            return Response.ok().entity(loginResponse).build();
        }
        return Response.status(403).build();
    }
}
