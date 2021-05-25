package edu.upc.dsa.database;

import edu.upc.dsa.models.User;

public interface UserDAO {
    int addUser(String username, String password, String fullName, String email);
    User getUser(int userId);
    int logIn(String username, String password);
}
