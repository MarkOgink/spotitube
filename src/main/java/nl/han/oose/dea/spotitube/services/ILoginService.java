package nl.han.oose.dea.spotitube.services;

import nl.han.oose.dea.spotitube.resources.LoginRequest;

import javax.ws.rs.core.Response;

public interface ILoginService {
    Response login(LoginRequest user);
}
