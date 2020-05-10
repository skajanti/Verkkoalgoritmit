package algotestit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.algot.Dijkstra;
import tira.reitinhaku.algot.JumpPoint;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.utils.KartanLataaja;

/**
 *
 * @author seppo
 */
public class JumpPointTest {
    @Test
    public void jpTest() {
        KartanLataaja k = new KartanLataaja();
        Verkko v = k.lataa("BigGameHunters.map");

        JumpPoint jp = new JumpPoint();
        
        long alku = System.currentTimeMillis();
        int[] reitti = jp.hae(v, 2, 30, 500, 80);
        long loppu = System.currentTimeMillis();

        int solmu = 500 + 80 * v.getY();
        
        int pituus = 0;
        do {
            int ss = reitti[solmu];
            solmu = ss;
            pituus++;
        } while (reitti[solmu] % v.getY() != 2 || reitti[solmu] / v.getY() != 30);
        
        assertTrue(pituus == 18);
        assertTrue("Liian hidas", loppu - alku < 225);
    }
}
