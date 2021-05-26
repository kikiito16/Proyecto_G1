package edu.upc.dsa;

import edu.upc.dsa.database.UserDAO;
import edu.upc.dsa.database.UserDAOImpl;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.api.CompleteCredentials;
import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {

    @Test
    public void test()
    {
        UserDAO dao = new UserDAOImpl();

        int id = dao.addUser("hola", "contra", "Pepito Luis", "pepe@gmail.com");

        User user = dao.getUser(id);
        if(user != null) {
            Assert.assertEquals("hola", user.getUsername());
            Assert.assertEquals("contra", user.getPassword());
            Assert.assertEquals("Pepito Luis", user.getFullName());
            Assert.assertEquals("pepe@gmail.com", user.getEmail());
        }

        int id2 = dao.addUser("hola", "contra2", "Pepito Luis", "pepe@gmail.com");
        Assert.assertEquals(-1, id2);

        int log1 = dao.logIn("noexiste", "holahola");
        Assert.assertEquals(-1, log1);
    }
}
