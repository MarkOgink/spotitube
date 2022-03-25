package nl.han.oose.dea.spotitube.util;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Token {
    public static Map<String,String> userMap = new HashMap<>();

    public static String setToken(String user){
        String token = UUID.randomUUID().toString();
        userMap.put(token, user);
        return token;
    }

    public static String getUsername(String token){
        return userMap.get(token);
    }

    public static boolean authenticate(String token){
        return userMap.containsKey(token);
    }
}
