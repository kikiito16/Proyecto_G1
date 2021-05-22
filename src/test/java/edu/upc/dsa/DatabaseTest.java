package edu.upc.dsa;

import edu.upc.dsa.database.UserDAO;
import edu.upc.dsa.database.UserDAOImpl;
import org.junit.Test;

import java.util.EmptyStackException;

public class DatabaseTest {

    @Test
    public void test()
    {
        UserDAO dao = new UserDAOImpl();

        dao.addUser("arnauem", "psw", "Arnau Esteban", "arnau@gmail.com");
    }
}
