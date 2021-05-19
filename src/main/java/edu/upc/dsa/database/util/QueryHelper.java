package edu.upc.dsa.database.util;

public class QueryHelper {

    public static String createQueryINSERT(Object object)
    {
        String query = "INSERT INTO " + object.getClass().getSimpleName() + " (id";

        String[] fields = ObjectHelper.getFields(object);

        for(String f: fields)
            query = query + "," + f;

        query = query + ") VALUES (?";

        for(int i = 0; i < fields.length; i++)
            query = query + ",?";

        query = query + ");";

        return query;
    }

}
