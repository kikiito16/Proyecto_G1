package edu.upc.dsa.database;

public interface Session {

    void create(Object entity);
    void delete(Object entity);
    void update(Object entity);
    void close();


}
