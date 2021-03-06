/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algotestit;

import java.util.IdentityHashMap;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.algot.Dijkstra;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.utils.KartanLataaja;

/**
 *
 * @author seppo
 */
public class DijkstraTest {
    @Test
    public void dijkstraTest() {
        KartanLataaja k = new KartanLataaja();
        Verkko v = k.lataa("BigGameHunters.map");

        Dijkstra a = new Dijkstra();
        
        long alku = System.currentTimeMillis();
        int[] reitti = a.hae(v, 2, 30, 500, 80);
        long loppu = System.currentTimeMillis();

        int solmu = 500 + 80 * v.getY();
        
        assertTrue(reitti[solmu] == 499 + 81 * v.getY());
        
        int pituus = 0;
        do {
            int ss = reitti[solmu];
            solmu = ss;
            pituus++;
        } while (reitti[solmu] % v.getY() != 2 || reitti[solmu] / v.getY() != 30);
        
        assertTrue(pituus == 497);
        assertTrue("Liian hidas", loppu - alku < 50);
    }
}
