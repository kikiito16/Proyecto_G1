package edu.upc.dsa;

import edu.upc.dsa.database.util.QueryHelper;
import edu.upc.dsa.models.CompleteCredentials;
import org.junit.Assert;
import org.junit.Test;

public class QueryHelperTest {

    @Test
    public void test()
    {
        CompleteCredentials completeCredentials = new CompleteCredentials("arnauem", "psw",
                "Arnau Esteban", "arnau@gmail.com");
        Assert.assertEquals("INSERT INTO CompleteCredentials (username,password,fullName,email) VALUES (?,?,?,?);"
                , QueryHelper.createQueryINSERT(completeCredentials));
    }
}
