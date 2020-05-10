package algotestit;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.algot.Verkko;

/**
 *
 * @author seppo
 */
public class VerkkoTest {
    @Test
    public void verkkoTest() {
        Verkko v = new Verkko(5, 5);
        boolean[][] b = new boolean[5][5];
        v.lisaaBoolKartta(b);
        
        assertTrue(!v.onkoSolmu(0, 0));
        assertTrue(!v.onkoSolmu(-1, -1));
        b[0][0] = true;
        assertTrue(v.onkoSolmu(0, 0));
        assertTrue(v.getPainot()[0][0] == Double.MAX_VALUE);
        assertTrue(v.getPaino(-1, -1) == Double.MAX_VALUE);
    }
}