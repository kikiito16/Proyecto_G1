package edu.upc.dsa.database;

import edu.upc.dsa.models.CompleteCredentials;

public interface UserDAO {
    void addUser(String username, String password, String fullName, String email);
}
