package edu.upc.dsa.database;

import edu.upc.dsa.database.util.ObjectHelper;
import edu.upc.dsa.database.util.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionImpl implements Session{

    Connection conn = null;

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
            preparedStatement.setObject(1, 0);

            int i = 2;

            for(String f: ObjectHelper.getFields(entity))
                preparedStatement.setObject(i++, ObjectHelper.getValue(entity, f));

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
        conn = null;
    }
}
