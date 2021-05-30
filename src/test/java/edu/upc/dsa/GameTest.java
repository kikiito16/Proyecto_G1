package edu.upc.dsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.upc.dsa.models.User;



public class GameTest {
    GameInterface gm;

    @Before
    public void setUp() throws Exception {
        gm = GameImpl.getInstance();

        User p1 = new User( "Enric", "kikito",0);
        User p2 = new User( "Marc", "cucu",1);


        gm.addPlayer(p1);
        gm.addPlayer(p2);

    }

    @After
    public void reset() {
        gm.clear();
    }

    @Test
    public void test1() {
        User p3 = new User( "Enric", "kikito",3);
        String usuario= "Enric";
        String psw = "kikito";
        gm.logIn(usuario,psw);

        //gm.signUp(2, "Enric", "kikito",3.0);

    }
}
