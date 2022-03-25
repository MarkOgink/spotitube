package nl.han.oose.dea.spotitube.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {
    String name;

    @BeforeEach
    void setUp() {
        this.name = "meron";
    }

    @Test
    void setToken() {
        String token = Token.setToken(name);
        assertEquals(name, Token.getUsername(token));
    }

    @Test
    void authenticate() {
        String token = Token.setToken(name);
        assertTrue(Token.authenticate(token));
    }

    @AfterEach
    void tearDown(){
    }
}
