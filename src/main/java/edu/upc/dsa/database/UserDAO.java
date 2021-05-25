package edu.upc.dsa.database;

import edu.upc.dsa.models.CompleteCredentials;
import edu.upc.dsa.models.Player;

public interface UserDAO {
    int addUser(String username, String password, String fullName, String email);
    CompleteCredentials getUser(int userId);
}
