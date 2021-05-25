package edu.upc.dsa.database;

import java.util.HashMap;

public interface Session {

    int create(Object entity);
    void delete(Object entity);
    void update(Object entity);
    void close();
    HashMap getBy(Class theClass, String attr, Object value);


}
