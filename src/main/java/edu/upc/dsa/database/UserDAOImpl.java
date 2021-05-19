package edu.upc.dsa.database;

import edu.upc.dsa.models.CompleteCredentials;

public class UserDAOImpl implements UserDAO{
    @Override
    public void addUser(String username, String password, String fullName, String email) {

        Session session = null;

        try
        {
            session = SessionFactory.openSession();
            CompleteCredentials completeCredentials = new CompleteCredentials(username, password, fullName, email);
            session.create(completeCredentials);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }
    }
}
