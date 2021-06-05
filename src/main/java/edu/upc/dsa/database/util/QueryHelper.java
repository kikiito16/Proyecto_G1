package edu.upc.dsa.database.util;

import java.util.List;

public class QueryHelper {

    public static String createQueryINSERT(Object object)
    {
        String query = "INSERT INTO " + object.getClass().getSimpleName() + " (";

        List<String> fields = ObjectHelper.getSimpleFields(object);

        query = query + fields.get(0);

        for(int i = 1; i < fields.size(); i++)
            query = query + "," + fields.get(i);


        query = query + ") VALUES (?";

        for(int i = 0; i < fields.size() - 1; i++)
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

    public static String createQueryUPDATE(Object object)
    {
        String query = "UPDATE " + object.getClass().getSimpleName() + " SET ";
        List<String> fields = ObjectHelper.getSimpleFields(object);

        for(String f : fields)
        {
            if(!f.equals("id"))
            query = query + f + "=?, ";
        }

        //erase the last ,
        query = query.substring(0,query.length()-2);

        query = query + " WHERE id=?;";

        return query;
    }

    public static String createQueryUPDATEAttribute(Class theClass, String attribute)
    {
        String query = "UPDATE " + theClass.getSimpleName() + " SET ";
        query = query + attribute + "=? WHERE id=?;";

        return query;
    }

    public static String createQueryDELETE(Class theClass)
    {
        String query = "DELETE FROM " + theClass.getSimpleName() +
                " WHERE id=?;";

        return query;
    }

    public static String createQueryDELETEWithCondition(Class theClass, String attribute)
    {
        String query = "DELETE FROM "+ theClass.getSimpleName() + " WHERE id=? AND " +
                attribute + "=?;";

        return query;
    }

}
