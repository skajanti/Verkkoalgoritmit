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
import tira.reitinhaku.algot.Solmu;
import tira.reitinhaku.algot.Verkko;

/**
 *
 * @author seppo
 */
public class DijkstraTest {
    
    @Test
    public void dijkstraTest() {
        Verkko v = new Verkko();
        
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                v.lisaaSolmu(new Solmu(j, i));
            }
        }
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                v.lisaaKaari(v.getSolmut().get(i*10 + j), v.getSolmut().get(i*10 + j + 1));
            }
        }
        
        v.lisaaKaari(v.getSolmut().get(54), v.getSolmut().get(55));
        v.lisaaKaari(v.getSolmut().get(55), v.getSolmut().get(56));
        
        for (int i = 0; i < 10; i++) {
            for (int j = 6; j < 9; j++) {
                v.lisaaKaari(v.getSolmut().get(i*10 + j), v.getSolmut().get(i*10 + j + 1));
            }
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                v.lisaaKaari(v.getSolmut().get(i*10 + j), v.getSolmut().get(i*10 + j + 10));
            }
        }
        
        Dijkstra d = new Dijkstra();
        IdentityHashMap<Solmu, Solmu> reitti = d.hae(v, v.getSolmut().get(0), v.getSolmut().get(07));
        int matka = 0;
        Solmu a = v.getSolmut().get(57);
        while (true) {
            Solmu s = reitti.get(a);
            if (s == null) break;
            
            a = s;
            matka++;
        }
        assertTrue(12 == matka);
    }
}
