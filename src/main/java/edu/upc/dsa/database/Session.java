package edu.upc.dsa.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session {

    int create(Object entity);
    int delete(Class theClass, int id);
    int update(Object entity);
    int update(Class theClass, int id, String attribute, Object value);
    void close();
    HashMap getBy(Class theClass, String attr, Object value);
    List<HashMap<String, Object>> getAllBy(Class theClass, String attr, Object value);
    int customUpdate(String query, Object ... objects);


}
