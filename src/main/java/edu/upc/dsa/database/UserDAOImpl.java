package edu.upc.dsa.database;

import edu.upc.dsa.models.CompleteCredentials;
import edu.upc.dsa.models.Player;

import java.util.HashMap;

public class UserDAOImpl implements UserDAO{

    //The user is added to the database, and its id is returned
    @Override
    public int addUser(String username, String password, String fullName, String email) {

        Session session = null;
        int userId = -1;

        try
        {
            session = SessionFactory.openSession();
            CompleteCredentials completeCredentials = new CompleteCredentials(username, password, fullName, email);
            session.create(completeCredentials);
            userId = (Integer) session.getBy(CompleteCredentials.class, "username", username).get("id");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }
        return userId;
    }

    @Override
    public CompleteCredentials getUser(int userId) {

        Session session = null;
        CompleteCredentials completeCredentials = null;
        try
        {
            session = SessionFactory.openSession();

            HashMap<String, Object> attributes = session.getBy(CompleteCredentials.class, "id", userId);
            completeCredentials = new CompleteCredentials(
                    attributes.get("username").toString(),
                    attributes.get("password").toString(),
                    attributes.get("fullName").toString(),
                    attributes.get("email").toString()
            );

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            session.close();
        }

        return completeCredentials;

    }
}
