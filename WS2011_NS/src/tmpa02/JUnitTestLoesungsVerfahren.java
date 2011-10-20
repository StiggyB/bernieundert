package tmpa02;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class JUnitTestLoesungsVerfahren {
    
    
    Funktion f0;
    LoesungsVerfahren regularfalsi;
    LoesungsVerfahren bisektion;
    LoesungsVerfahren sekante;
    LoesungsVerfahren fixpunkt;
    
    
    double aIntervall;
    double bIntervall;
    int iterationen;
    
    @Before
    public void setUp() throws Exception {
        f0 = new F0Funktion();
        regularfalsi = new RegulaFalsiVerfahren();
        bisektion    = new BisektionsVerfahren();
        sekante      = new SekantenVerfahren();
        fixpunkt     = new FixPunktVerfahren();
        
        aIntervall = -3;
        bIntervall =  -1.5;
        iterationen = 25;
    }

    @Test
    public void testRegularFalsiVerfahren() {
        Assert.assertEquals(2, Math.abs(regularfalsi.solve(f0, aIntervall, bIntervall, iterationen)), 0.0000001);
    }

    @Test
    public void testSekantenVerfahren() {
        Assert.assertEquals(2, Math.abs(sekante.solve(f0, aIntervall, bIntervall, iterationen)), 0.0000001);
    }
    
    @Test
    public void testBisektionsVerfahren() {
        Assert.assertEquals(2, Math.abs(bisektion.solve(f0, aIntervall, bIntervall, iterationen)), 0.0000001);
    }
    
    @Test
    public void testFixPunktVerfahren() {
        Assert.assertEquals(2, Math.abs(fixpunkt.solve(f0, aIntervall, bIntervall, iterationen)), 0.0000001);
    }
}
