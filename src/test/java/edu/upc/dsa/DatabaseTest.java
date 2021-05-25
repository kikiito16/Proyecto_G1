package edu.upc.dsa;

import edu.upc.dsa.database.UserDAO;
import edu.upc.dsa.database.UserDAOImpl;
import edu.upc.dsa.models.CompleteCredentials;
import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class DatabaseTest {

    @Test
    public void test()
    {
        UserDAO dao = new UserDAOImpl();

        int id = dao.addUser("martaculogordo", "princess", "Marta Fea", "marta@gmail.com");

        CompleteCredentials cred = dao.getUser(id);
        Assert.assertEquals("martaculogordo", cred.getUsername());
        Assert.assertEquals("princess", cred.getPassword());
        Assert.assertEquals("Marta Fea", cred.getFullName());
        Assert.assertEquals("marta@gmail.com", cred.getEmail());
    }
}
