package edu.upc.dsa.database;

import edu.upc.dsa.models.User;

public interface UserDAO {
    int addUser(String username, String password, String fullName, String email);
    User getUser(int userId);
    User getUser(String username);
    int logIn(String username, String password);
    int updateUser(int id, String username, String fullName, String email, int money);
    int updateUserAttribute(int id, String attribute, Object value);
}
