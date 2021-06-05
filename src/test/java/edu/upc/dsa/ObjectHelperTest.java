package edu.upc.dsa;

import edu.upc.dsa.database.util.ObjectHelper;
import edu.upc.dsa.models.User;
import org.junit.Test;

import java.util.List;

public class ObjectHelperTest {

    @Test
    public void getSimpleFieldsTest()
    {
        User u = new User();
        List<String> simpleFields = ObjectHelper.getSimpleFields(u);
        List<String> fields = ObjectHelper.getFields(u);
        System.out.println("Simple fields: ");
        for(String f : simpleFields)
        {
            System.out.println(f);
        }
        System.out.println("All fields: ");
        for(String f : fields)
        {
            System.out.println(f);
        }
    }

}
