package algotestit;

import java.util.IdentityHashMap;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.algot.AStar;

public class AStarTest {
    @Test
    public void oktiiliTest() {
        AStar a = new AStar();
        double d = a.oktiiliMatka(0, 0, 1, 1);
        assertTrue(d == Math.sqrt(2));
        d = a.oktiiliMatka(0, 0, 0, 1);
        assertTrue(d == 1.0);
    }
}
