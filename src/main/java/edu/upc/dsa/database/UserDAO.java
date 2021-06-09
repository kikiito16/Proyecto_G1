package edu.upc.dsa.database;

import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Object;
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

    int addToInventory(List<Object> objectList, int userId);
    List<FullObject> getInventoryOf(int userId);

    int addGame(int playerId, int duration, int victory, int score);
    Game getGame(int id);
    List<Game> getAllGamesOf(int playerId);

}
