package edu.upc.dsa.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;

public interface Session {

    int create(Object entity);
    int delete(Class theClass, int id);
    int delete(Class theClass, Object... objects); //objects args: String attribute, value (for each WHERE condition)
    int customDelete(String query, Object ... objects);
    int update(Object entity);
    int update(Class theClass, int id, String attribute, Object value);
    void close();
    HashMap getBy(Class theClass, String attr, Object value);
    List<HashMap<String, Object>> getAllBy(Class theClass, String attr, Object value);
    List<HashMap<String, Object>> getAll(Class theClass);
    int customUpdate(String query, Object ... objects) throws SQLIntegrityConstraintViolationException;
    ResultSet customQuery(String query, List<Object> objects);
    ResultSet customQuery(String query, Object ... objects);
    ResultSet getBy(Class theClass, String targetAttributes, Object ... conditions);

}
