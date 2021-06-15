package edu.upc.dsa.database;

import edu.upc.dsa.database.util.ObjectHelper;
import edu.upc.dsa.database.util.QueryHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionImpl implements Session{

    private final Connection conn;

    public SessionImpl(Connection conn)
    {
        this.conn = conn;
    }

    //-2 --> error
    //-1 --> username already exists or foreign key error or SQLIntegrityConstraintViolationException
    //0 inserted correctly
    @Override
    public int create(Object entity) {
        PreparedStatement preparedStatement = null;

        String query = QueryHelper.createQueryINSERT(entity);

        try
        {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;

            List<String > fields = ObjectHelper.getSimpleFields(entity);
            for(String f: fields)
            {
                if(f.equals("id"))
                    preparedStatement.setNull(i, Types.NULL);
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
            e.printStackTrace();
            return -1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -2;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -2;
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
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    @Override
    public int delete(Class theClass, Object... objects) {

        //The name of the attributes are stored in a list
        List<String> attributes = new ArrayList();
        for(int i = 0; i < objects.length; i+=2)
            attributes.add(objects[i].toString());

        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQueryDELETE(theClass, attributes);

        try
        {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;
            for(int j = 1; j < objects.length; j += 2)
                preparedStatement.setObject(i++, objects[j]);

            preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }

        return 0;

    }

    //0 successful
    //-1 error
    @Override
    public int customDelete(String query, Object... objects) {

        PreparedStatement preparedStatement = null;

        try
        {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;
            for(Object o : objects)
                preparedStatement.setObject(i++, o);

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
            List<String> fields = ObjectHelper.getSimpleFields(entity);

            int i = 1;

            for(String f : fields)
            {
                if(!f.equals("id") && !f.equals("password")) {
                    preparedStatement.setObject(i, ObjectHelper.getValue(entity, f));
                    i++;
                }
            }
            preparedStatement.setObject(i, ObjectHelper.getValue(entity, "id"));

            boolean error = preparedStatement.execute();
            if(!error)
                return 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        catch (Exception e)
        {
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
        catch (Exception e)
        {
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
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return attributes;


    }

    //null -> not found
    @Override
    public List<HashMap<String, Object>> getAllBy(Class theClass, String attr, Object value) {

        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQuerySELECT(theClass, attr);
        List<HashMap<String, java.lang.Object>> list = new ArrayList<>();

        try
        {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                HashMap<String, java.lang.Object> attributes = new HashMap<>();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    attributes.put(resultSetMetaData.getColumnName(i + 1), resultSet.getObject(i + 1));
                }
                list.add(attributes);
            }

            if(!resultSet.first())
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    @Override
    public List<HashMap<String, Object>> getAll(Class theClass) {
        PreparedStatement preparedStatement = null;
        String query = QueryHelper.createQuerySELECTAll(theClass);
        List<HashMap<String, java.lang.Object>> list = new ArrayList<>();

        try
        {
            preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                HashMap<String, java.lang.Object> attributes = new HashMap<>();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    attributes.put(resultSetMetaData.getColumnName(i + 1), resultSet.getObject(i + 1));
                }
                list.add(attributes);
            }

            if(!resultSet.first())
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    //It returns the number of updated rows
    //-1 --> error
    @Override
    public int customUpdate(String query, Object... objects) throws SQLIntegrityConstraintViolationException{

        //We prepare the query and execute it
        PreparedStatement preparedStatement = null;
        int affectedRows = -1;

        try {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;
            for (Object o : objects) {
                preparedStatement.setObject(i++, o);
            }

            //We store the number of affectedRows
            affectedRows = preparedStatement.executeUpdate();
        }
        catch(SQLIntegrityConstraintViolationException e)
        {
            throw e;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRows;
    }

    @Override
    public ResultSet customQuery(String query, List<Object> objects) {
        //We prepare the query and execute it
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;
            for (Object o : objects) {
                preparedStatement.setObject(i++, o);
            }

            //We store the number of affectedRows
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    @Override
    public ResultSet customQuery(String query, Object ... objects) {
        //We prepare the query and execute it
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;
            for (Object o : objects) {
                preparedStatement.setObject(i++, o);
            }

            //We store the number of affectedRows
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    //Args: class, "id, price", "id", 1, "name", "Charlie"...
    //-1 --> error
    @Override
    public ResultSet getBy(Class theClass, String targetAttributes, Object... conditions) {

        //We store the name of every attribute of each condition in a list attributes
        String[] attributes = new String[conditions.length/2];
        int j = 0;
        for(int i = 0; i < conditions.length; i+=2)
            attributes[j++] = conditions[i].toString();

        //We create the query
        String query = QueryHelper.createQuerySELECT(theClass, targetAttributes, attributes);
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        try
        {
            preparedStatement = conn.prepareStatement(query);
            j = 1;
            for(int i = 1; i < conditions.length; i+=2)
                preparedStatement.setObject(j++, conditions[i]);

            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;

    }


}
