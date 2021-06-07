package edu.upc.dsa;

import edu.upc.dsa.database.*;
import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.api.CompleteCredentials;
import edu.upc.dsa.models.api.Inventory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Test
    public void updateUserTest()
    {
        User user = new User("martapm", "contra_marta", "Marta Pardo", "marta@gmail.com");
        user.setId(36);
        user.setMoney(125);

        UserDAO dao = new UserDAOImpl();
        int res = dao.updateUser(36, "martapm", "Marta Pardo", "marta@gmail.com", 125);
        User user1 = dao.getUser(36);

        Assert.assertEquals(36, user1.getId());
        Assert.assertEquals("martapm", user1.getUsername());
        Assert.assertEquals("contra_marta", user1.getPassword());
        Assert.assertEquals("Marta Pardo", user1.getFullName());
        Assert.assertEquals("marta@gmail.com", user1.getEmail());
        Assert.assertEquals(125, user1.getMoney());
    }

    @Test
    public void updateUserAttributeTest()
    {
        UserDAO dao = new UserDAOImpl();
        dao.updateUserAttribute(36, "fullName", "Marta Juan");

        User user = dao.getUser(36);
        Assert.assertEquals("Marta Juan", user.getFullName());
    }

    @Test
    public void deleteUserTest()
    {
        UserDAO dao = new UserDAOImpl();
        int res = dao.deleteUser(5);
        Assert.assertEquals(0, res);
    }

    @Test
    public void InventoryTest()
    {
        UserDAO dao = new UserDAOImpl();
        List<FullObject> list = dao.getInventoryOf(1);

        List<Object> objectList = new ArrayList<>();
        objectList.add(new Object(1, 1));
        objectList.add(new Object(2, 2));
        objectList.add(new Object(3, 3));
        int res = dao.addToInventory(objectList, 50);
        Assert.assertEquals(-1, res);

        res = dao.addToInventory(objectList, 2);
        Assert.assertEquals(0, res);
    }
}