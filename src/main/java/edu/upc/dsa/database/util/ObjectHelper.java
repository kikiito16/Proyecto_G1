package edu.upc.dsa.database.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectHelper {

    public static List<String> getSimpleFields(java.lang.Object object)
    {
        Field[] fields = object.getClass().getDeclaredFields();

        List<String> sFields = new ArrayList();

        for(Field f : fields)
        {
            if(f.getType() != List.class)
                sFields.add(f.getName());
        }

        return sFields;

    }

    public static List<String> getFields(java.lang.Object object)
    {
        Field[] fields = object.getClass().getDeclaredFields();

        List<String> sFields = new ArrayList<>();
        int i = 0;

        for(Field f : fields)
        {
            sFields.add(f.getName());
        }

        return sFields;

    }

    public static Object getValue (Object object, String property) {

        try {

            Field field = object.getClass().getDeclaredField(property);
            field.setAccessible(true);
            Object value = field.get(object);

            return value;
        }
        catch(NoSuchFieldException | IllegalAccessException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
