package edu.upc.dsa;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.upc.dsa.database.*;
import edu.upc.dsa.models.*;
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
        UserDAO dao = new UserDAOImpl();
        int res = dao.updateUser(2, "martapm2", "Marta Pardo", "marta@gmail.com", 125);
        User user1 = dao.getUser(2);

        Assert.assertEquals(2, user1.getId());
        Assert.assertEquals("martapm2", user1.getUsername());
        Assert.assertEquals("4d186321c1a7f0f354b297e8914ab240", user1.getPassword());
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
        int res = dao.deleteUser(1);
        Assert.assertEquals(0, res);
    }

    @Test
    public void InventoryTest()
    {
        UserDAO dao = new UserDAOImpl();
        List<FullObject> list = dao.getInventoryOf(1);

        List<GameObject> objectList = new ArrayList<>();
        objectList.add(new GameObject(1, 1));
        objectList.add(new GameObject(2, 2));
        objectList.add(new GameObject(3, 3));
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

    @Test
    public void customUpdate()
    {
        UserDAO dao = new UserDAOImpl();
        List<GameObject> objectList = new ArrayList<>();
        objectList.add(new GameObject(1, 25));
        objectList.add(new GameObject(2, 40));
        int res = dao.addToInventory(objectList, 1);
        System.out.println(res);
    }

    @Test
    public void useObject()
    {
        UserDAO dao = new UserDAOImpl();
        Assert.assertEquals(0, dao.useObject(1, 1));
        Assert.assertEquals(0, dao.useObject(3, 2));
        Assert.assertEquals(-1, dao.useObject(1, 3));
    }

    @Test
    public void getStoreObjectsTest()
    {
        UserDAO dao = new UserDAOImpl();
        List<FullObject> storeObjects = dao.getStoreObjects();
        List<FullObject> user1Objects = dao.getInventoryOf(1);
        List<FullObject> user2Objects = dao.getInventoryOf(2);
        List<FullObject> unknownObjects = dao.getInventoryOf(9);

    }

    @Test
    public void buyObjectTest()
    {
        UserDAO dao =  new UserDAOImpl();
        List<GameObject> list = new ArrayList<>();
        list.add(new GameObject(6, 2)); //price per each = 1
        list.add(new GameObject(7,1)); //price per each = 5
        int res = dao.buyObject(1, list);

        list = new ArrayList<>();
        list.add(new GameObject(11, 1));
        res = dao.buyObject(1, list);
    }

    @Test
    public void sellObjectTest()
    {
        UserDAO dao = new UserDAOImpl();
        int res = dao.sellObject(1, new GameObject(5, 2)); //-2
        Assert.assertEquals(-2, res);

        res = dao.sellObject(1, new GameObject(5,1));
        Assert.assertEquals(32, res);

        res = dao.sellObject(3, new GameObject(1,1));
        Assert.assertEquals(-1, res);

        res = dao.sellObject(2, new GameObject(10,2));
        Assert.assertEquals(29, res);

        res = dao.sellObject(2, new GameObject(3, 3));
        Assert.assertEquals(59, res);

    }

    @Test
    public void mapTest()
    {
        UserDAO dao = new UserDAOImpl();
        Map map = dao.getMap(2);
    }

}