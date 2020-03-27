package algotestit;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tira.reitinhaku.algot.Kaari;
import tira.reitinhaku.algot.Solmu;
import tira.reitinhaku.algot.Verkko;

/**
 *
 * @author seppo
 */
public class VerkkoTest {
    @Test
    public void constructorTest() {
        Verkko v = new Verkko(5, 5);
        v.lisaaSolmu(new Solmu(1, 1), 1, 1);
        assertEquals(v.getSolmu(1, 1).getX(), 1);
    }
    
    @Test
    public void kaariTest() {
        Verkko v = new Verkko(5, 5);
        v.lisaaSolmu(new Solmu(1, 1), 1, 1);
        v.lisaaSolmu(new Solmu(2, 2), 2, 2);
        v.lisaaKaari(v.getSolmu(1, 1), v.getSolmu(2, 2));
        
        ArrayList<Kaari> arr = v.getKaaret();
        assertEquals(arr.get(0).getA().getX(), 1);
    }
}
