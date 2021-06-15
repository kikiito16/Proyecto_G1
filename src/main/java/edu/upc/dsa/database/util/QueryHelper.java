package edu.upc.dsa.database.util;

import java.util.List;

public class QueryHelper {

    public static String createQueryINSERT(Object object)
    {
        String query = "INSERT INTO " + object.getClass().getSimpleName() + " (";

        List<String> fields = ObjectHelper.getSimpleFields(object);

        int passwordIndex = -1;

        //We check if a password attribute is in field[0]
        if(fields.get(0).equals("password"))
            passwordIndex = 0;

        query = query + fields.get(0);

        for(int i = 1; i < fields.size(); i++) {

            //We check if there is a password attribute, in order to encrypt it later
            if(fields.get(i).equals("password"))
                passwordIndex = i;

            //attribute name added to the query string
            query = query + "," + fields.get(i);
        }


        if(passwordIndex == 0)
            query = query + ") VALUES (MD(?)";
        else
            query = query + ") VALUES (?";

        for(int i = 0; i < fields.size() - 1; i++) {

            //We encrypt the password if there is
            if (i == passwordIndex-1)
                query = query + ",MD5(?)";

            else
                query = query + ",?";
        }

        query = query + ");";

        return query;
    }

    public static String createQueryDELETE(Object object)
    {
        String query = "DELETE FROM " + object.getClass().getSimpleName() + " WHERE id=?;";

        return query;
    }

    public static String createQueryDELETE(Class theClass, List<String> attributes)
    {
        String query = "DELETE FROM " + theClass.getSimpleName() + " WHERE ";
        for(Object a : attributes)
        {
            query = query + a.toString() + "=? ";
        }
        query = query + ";";

        return query;
    }

    public static String createQuerySELECT(Class theClass, String field)
    {
        String query = "SELECT * from " + theClass.getSimpleName() + " WHERE " + field +
                "=?;";

        return query;
    }

    public static String createQuerySELECT(Class theClass, String targetAttributes, String... conditions)
    {
        String query = "SELECT " + targetAttributes + " FROM " + theClass.getSimpleName() + " WHERE ";
        for(String c : conditions)
            query = query + c + "=? AND ";

        query = query.substring(0, query.length()-5);

        query = query + ";";

        return query;
    }

    public static String createQuerySELECTAll(Class theClass)
    {
        return "SELECT * FROM " + theClass.getSimpleName() + ";";
    }

    //password not updated
    public static String createQueryUPDATE(Object object)
    {
        String query = "UPDATE " + object.getClass().getSimpleName() + " SET ";
        List<String> fields = ObjectHelper.getSimpleFields(object);

        for(String f : fields)
        {
            if(!f.equals("id") && !f.equals("password"))
                query = query + f + "=?, ";
        }

        //erase the last ','
        query = query.substring(0,query.length()-2);

        query = query + " WHERE id=?;";

        return query;
    }

    public static String createQueryUPDATEAttribute(Class theClass, String attribute)
    {
        String query = "UPDATE " + theClass.getSimpleName() + " SET ";
        if(attribute == "password")
            query = query + attribute + "=MD5(?) WHERE id=?;";
        else
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
