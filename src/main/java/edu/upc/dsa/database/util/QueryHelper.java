package edu.upc.dsa.database.util;

public class QueryHelper {

    public static String createQueryINSERT(Object object)
    {
        String query = "INSERT INTO " + object.getClass().getSimpleName() + " (";

        String[] fields = ObjectHelper.getFields(object);

        query = query + fields[0];

        for(int i = 1; i < fields.length; i++)
            query = query + "," + fields[i];


        query = query + ") VALUES (?";

        for(int i = 0; i < fields.length - 1; i++)
            query = query + ",?";

        query = query + ");";

        return query;
    }

    public static String createQueryDELETE(Object object)
    {
        String query = "DELETE FROM " + object.getClass().getSimpleName() + " WHERE id=?;";

        return query;
    }

    public static String createQuerySELECT(Class theClass, String field)
    {
        String query = "SELECT * from " + theClass.getSimpleName() + " WHERE " + field +
                "=?;";

        return query;
    }

}
