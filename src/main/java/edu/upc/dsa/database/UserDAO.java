package edu.upc.dsa.database;

import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserDAO {
    int addUser(String username, String password, String fullName, String email);
    int addToInventory(List<Object> objectList, int userId);
    List<FullObject> getInventoryOf(int userId);
    User getUser(int userId);
    User getUser(String username);
    int deleteUser(int id);
    int logIn(String username, String password);
    int updateUser(int id, String username, String fullName, String email, int money);
    int updateUserAttribute(int id, String attribute, java.lang.Object value);
}
