package algotestit;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.algot.Solmu;

public class SolmuTest {
    
    @Test
    public void constructorTest() {
        Solmu s = new Solmu(1, 5);
        assertTrue(1 == s.getX());
        assertTrue(5 == s.getY());
        assertTrue(s.getKaaret().isEmpty());
        assertTrue(Double.MAX_VALUE == s.getMatka());
    }
    
    @Test
    public void matkaTest() {
        Solmu s = new Solmu(1, 5);
        s.setMatka(5.5);
        assertTrue(5.5 == s.getMatka());
        Solmu t = new Solmu(1, 2);
        t.setMatka(2.2);
        assertTrue(s.compare(s, t) > 0);
    }
    
    @Test 
    public void equalsTest() {
        Solmu s = new Solmu(1, 5);
        Solmu t = new Solmu(1, 5);
        assertTrue(s.equals(t));
        t.setMatka(5.5);
        assertTrue(!s.equals(t));
        t = null;
        Double d = 0.0;
        assertTrue(!s.equals(t));
        assertTrue(!s.equals(d));
    }
    
    @Test
    public void hashCodeTest() {
        Solmu s = new Solmu(0, 1);
        ArrayList<Solmu> arr = new ArrayList();
        
        for (int i = 0; i < 100; i++) {
            arr.add(new Solmu(5, i));
        }
        
        for (Solmu t : arr) {
            assertTrue(s.hashCode() != t.hashCode());
        }
    }
}
