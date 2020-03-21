package algotestit;

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
    }
    
    @Test 
    public void equalsTest() {
        Solmu s = new Solmu(1, 5);
        Solmu t = new Solmu(1, 5);
        assertTrue(s.equals(t));
        t.setMatka(5.5);
        assertTrue(!s.equals(t));
    }
}
