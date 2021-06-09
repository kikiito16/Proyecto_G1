package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.api.CompleteCredentials;

import java.util.List;

public interface GameInterface {

    //Related to authService
    int logIn  (String username, String password);
    int signUp (CompleteCredentials newUsr);
    int logOut(String username);

    //Related to userService
    User getUser (int ID);
    int deleteUser(int id);
    int updateUser(int id, String username, String fullName, String email, int money);
    int updateUserAttribute(int id, String attribute, java.lang.Object value);

    //Related to objectService
    Object buyObject (String object);
    int addObject (List<Object> objectList, int userId);
    Object useObject (String object);
    List<FullObject> getUserObjects (int id);

    //Related to gameService
    int addGame(int playerId, int duration, int victory, int score);
    Game getGame(int id);
    List<Game> getAllGamesOf(int playerId);

    //Related to mapsService
    Map getMap (int idMap);

    //Internal calls
    List<String> addConnected (String user);
    void Disconnect(int pos);
    void clear();
}
