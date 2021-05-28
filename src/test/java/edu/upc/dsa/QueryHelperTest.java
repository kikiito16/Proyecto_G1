package edu.upc.dsa;

import edu.upc.dsa.database.util.QueryHelper;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.api.CompleteCredentials;
import org.junit.Assert;
import org.junit.Test;

public class QueryHelperTest {

    @Test
    public void testQueryCREATE()
    {
        CompleteCredentials completeCredentials = new CompleteCredentials("arnauem", "psw",
                "Arnau Esteban", "arnau@gmail.com");
        Assert.assertEquals("INSERT INTO CompleteCredentials (username,password,fullName,email) VALUES (?,?,?,?);"
                , QueryHelper.createQueryINSERT(completeCredentials));
    }

    @Test
    public void testQueryUPDATE()
    {
        User user = new User("arnauem", "1234", "Arnau", "arnau@gmail.com");
        user.setId(3);

        Assert.assertEquals("UPDATE User SET username=?, password=?, fullName=?, email=?, money=? "+
                "WHERE id=?;", QueryHelper.createQueryUPDATE(user));
    }
}
