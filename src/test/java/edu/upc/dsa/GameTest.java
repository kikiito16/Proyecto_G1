package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import edu.upc.dsa.models.Player;



public class GameTest {
    GameInterface gm;

    @Before
    public void setUp() throws Exception {
        gm = GameImpl.getInstance();

        Player p1 = new Player( "Enric", "kikito",0);
        Player p2 = new Player( "Marc", "cucu",1);


        gm.addPlayer(p1);
        gm.addPlayer(p2);

    }

    @After
    public void reset() {
        gm.clear();
    }

    @Test
    public void test1() {
        Player p3 = new Player( "Enric", "kikito",3);
        String usuario= "Enric";
        String psw = "kikito";
        gm.logIn(usuario,psw);

        //gm.signUp(2, "Enric", "kikito",3.0);

    }

    @Test
    public void test2() {
        //Player p4 = new Player(1, "Marc", "cucu",3.0);
        gm.deletePlayer("Marc","cucu");
    }
}
