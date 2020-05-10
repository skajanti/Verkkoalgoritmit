package tiratestit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.tirat.Keko;

/**
 *
 * @author seppo
 */
public class KekoTest {
    @Test
    public void lisaysTest() {
        Keko k = new Keko(5, 5);
        assertTrue(k.isEmpty());
        k.lisaa(0, 0, 4.1);
        k.lisaa(3, 0, 2.1);
        k.lisaa(4, 0, 1.1);
        k.lisaa(2, 0, 4.1);
        
        assertTrue(!k.remove(5, 5));
        
        assertTrue(k.peekInt() == 4);
        
        assertTrue(k.toArray()[0] == 1.1);
    }
}
