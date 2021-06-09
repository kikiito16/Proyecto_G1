package edu.upc.dsa;

import edu.upc.dsa.database.*;
import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTest {

    @Test
    public void test() throws NoSuchAlgorithmException {
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

    @Test
    public void GameTest()
    {
        UserDAO dao = new UserDAOImpl();
        int res = dao.addGame(50, 76, 1, 106);
        Assert.assertEquals(-1, res);

        int res2 = dao.addGame(1, 76, 1, 106);
        Assert.assertEquals(0, res2);

        int res3 = dao.addGame(2, 57, 0, 19);
        Assert.assertEquals(0, res3);

        int res4 = dao.addGame(1, 60, 0, 17);
        Assert.assertEquals(0, res4);

        Game game2 = dao.getGame(4);

        List<Game> gameList = dao.getAllGamesOf(1);
    }
}