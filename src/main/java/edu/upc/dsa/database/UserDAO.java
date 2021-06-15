package edu.upc.dsa.database;

import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.GameObject;
import edu.upc.dsa.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserDAO {
    int addUser(String username, String password, String fullName, String email);
    int logIn(String username, String password) throws NoSuchAlgorithmException;

    User getUser(int userId);
    User getUser(String username);

    int deleteUser(int id);

    int updateUser(int id, String username, String fullName, String email, int money);
    int updateUserAttribute(int id, String attribute, java.lang.Object value);

    int addToInventory(List<GameObject> objectList, int userId);
    int useObject(int objectId, int userId);
    List<FullObject> getInventoryOf(int userId);
    List<FullObject> getStoreObjects();
    int buyObject(int userId, List<GameObject> objects);
    int sellObject(int userId, GameObject gameObject);

    int addGame(int playerId, int duration, int victory, int score);
    Game getGame(int id);
    List<Game> getAllGamesOf(int playerId);

}
