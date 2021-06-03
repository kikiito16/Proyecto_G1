package edu.upc.dsa.database;

import edu.upc.dsa.database.util.ObjectHelper;
import edu.upc.dsa.database.util.QueryHelper;
import edu.upc.dsa.models.User;

import java.sql.*;
import java.util.HashMap;

public class SessionImpl implements Session{

    private final Connection conn;

    public SessionImpl(Connection conn)
    {
        this.conn = conn;
    }

    //-1 --> username already exists
    //0 inserted correctly
    @Override
    public int create(Object entity) {
        PreparedStatement preparedStatement = null;

        String query = QueryHelper.createQueryINSERT(entity);

        try
        {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;

            String[] fields = ObjectHelper.getFields(entity);
            for(String f: fields)
            {
                if(f.equals("id"))
                    preparedStatement.setNull(1, Types.NULL);
                else {
                    java.lang.Object object = ObjectHelper.getValue(entity, f);
                    preparedStatement.setObject(i, object);
                }
                i++;
            }

            preparedStatement.executeQuery();

        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            return -1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    //-1 error
    //0 successful
    @Override
    public int delete(Class theClass, int id) {

        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQueryDELETE(theClass);

        try
        {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    //0 --> successful
    //-1 --> error
    @Override
    public int update(Object entity) {

        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQueryUPDATE(entity);
        ResultSet resultSet;

        try
        {
            preparedStatement = conn.prepareStatement(query);
            String[] fields = ObjectHelper.getFields(entity);

            int i = 1;

            for(String f : fields)
            {
                if(!f.equals("id")) {
                    preparedStatement.setObject(i, ObjectHelper.getValue(entity, f));
                    i++;
                }
            }
            preparedStatement.setObject(fields.length, ObjectHelper.getValue(entity, "id"));

            boolean error = preparedStatement.execute();
            if(!error)
                return 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }


    //-1 --> error
    //0 successful
    @Override
    public int update(Class theClass, int id, String attribute, Object value) {

        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQueryUPDATEAttribute(theClass, attribute);
        ResultSet resultSet;

        try
        {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, value);
            preparedStatement.setInt(2, id);

            preparedStatement.executeQuery();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    @Override
    public void close() {

    }

    //Returns hashmap<String attribute, object value>
    //null if no results
    @Override
    public HashMap getBy(Class theClass, String attr, Object value) {

        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQuerySELECT(theClass, attr);
        HashMap<String, java.lang.Object> attributes = new HashMap<>();;

        try
        {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.first()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    attributes.put(resultSetMetaData.getColumnName(i + 1), resultSet.getObject(i + 1));
                }
            }
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attributes;


    }



}
