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

        Player p1 = new Player(0, "Enric", "kikito",3.0);
        Player p2 = new Player(1, "Marc", "cucu",3.0);


        gm.addJugador(p1);
        gm.addJugador(p2);

    }

    @After
    public void reset() {
        gm.clear();
    }

    @Test
    public void test1() {

    }
}
