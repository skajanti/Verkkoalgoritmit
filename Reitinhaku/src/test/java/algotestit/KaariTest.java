/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algotestit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.algot.Kaari;
import tira.reitinhaku.algot.Solmu;

/**
 *
 * @author seppo
 */
public class KaariTest {
    
    @Test
    public void constructorTest() {
        Solmu a = new Solmu(1, 1);
        Solmu b = new Solmu(2, 2);
        Kaari k = new Kaari(a, b);
        assertTrue(k.getA() == a);
        assertEquals(k.getB(), b);
    }
    
    @Test
    public void painoTest() {
        Solmu a = new Solmu(1, 1);
        Solmu b = new Solmu(2, 2);
        Kaari k = new Kaari(a, b);
        assertTrue(k.getPaino() == Math.sqrt(2));
    }
}
