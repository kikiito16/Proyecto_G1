package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.GameObject;
import edu.upc.dsa.models.api.CompleteCredentials;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface GameInterface {

    //Related to authService
    int logIn  (String username, String password) throws NoSuchAlgorithmException;
    int signUp (CompleteCredentials newUsr);

    //Related to userService
    User getUser (int ID);
    int deleteUser(int id);
    int updateUser(int id, String username, String fullName, String email, int money);
    int updateUserAttribute(int id, String attribute, java.lang.Object value);

    //Related to objectService
    int buyObject (List<GameObject> object, int id);
    int sellObject (List<GameObject> object, int id);
    int addObject (List<GameObject> objectList, int userId);
    int useObject (int objectId, int userId);
    List<FullObject> getUserObjects (int id);
    List<FullObject> getStoreObjects();
    int buyObject(int userId, List<GameObject> objects);
    int sellObject(int userId, GameObject gameObject);

    //Related to gameService
    int addGame(int playerId, int duration, int victory, int score);
    Game getGame(int id);
    List<Game> getAllGamesOf(int playerId);

    //Related to mapsService
    Map getMap (int idMap);
}
