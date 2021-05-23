package edu.upc.dsa.database;

import edu.upc.dsa.database.util.ObjectHelper;
import edu.upc.dsa.database.util.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                Object object = ObjectHelper.getValue(entity, f);
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
    public void delete(Object object)
    {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void close() {

    }
}
