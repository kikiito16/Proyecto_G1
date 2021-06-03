package edu.upc.dsa.database;

import java.util.HashMap;

public interface Session {

    int create(Object entity);
    int delete(Class theClass, int id);
    int update(Object entity);
    int update(Class theClass, int id, String attribute, Object value);
    void close();
    HashMap getBy(Class theClass, String attr, Object value);


}
