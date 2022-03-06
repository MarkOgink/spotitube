package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.domain.User;
import nl.han.oose.dea.spotitube.resources.LoginRequest;
import nl.han.oose.dea.spotitube.resources.LoginResponse;

import javax.ws.rs.core.Response;

public interface ILoginService {
    User login(LoginRequest user);
}
