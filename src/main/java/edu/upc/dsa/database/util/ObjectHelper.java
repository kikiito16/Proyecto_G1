package edu.upc.dsa.database.util;

import java.lang.reflect.Field;

public class ObjectHelper {

    public static String[] getFields(java.lang.Object object)
    {
        Field[] fields = object.getClass().getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i = 0;

        for(Field f : fields)
        {
            sFields[i++] = f.getName();
        }

        return sFields;

    }

    public static Object getValue (Object object, String property) {

        try {
            Object value = object.getClass().getDeclaredField(property);
            return value;
        }
        catch(NoSuchFieldException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
