package edu.upc.dsa.database;

import edu.upc.dsa.database.util.ObjectHelper;
import edu.upc.dsa.database.util.QueryHelper;
import edu.upc.dsa.models.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;

public class SessionImpl implements Session{

    private final Connection conn;

    public SessionImpl(Connection conn)
    {
        this.conn = conn;
    }


    @Override
    public void create(Object entity) {
        PreparedStatement preparedStatement = null;

        String query = QueryHelper.createQueryINSERT(entity);

        try
        {
            preparedStatement = conn.prepareStatement(query);

            int i = 1;

            String[] fields = ObjectHelper.getFields(entity);
            for(String f: fields)
            {
                java.lang.Object object = ObjectHelper.getValue(entity, f);
                preparedStatement.setObject(i++, object);
            }

            preparedStatement.executeQuery();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void close() {

    }

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
            resultSet.first();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            for(int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                attributes.put(resultSetMetaData.getColumnName(i+1), resultSet.getObject(i+1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attributes;


    }



}
