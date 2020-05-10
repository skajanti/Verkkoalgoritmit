package tiratestit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import tira.reitinhaku.tirat.ExtendingList;

/**
 *
 * @author seppo
 */
public class listtest {
    @Test
    public void listaTest() {
        ExtendingList<Integer> arr = new ExtendingList();
        arr.add(1);
        assertTrue(arr.length() == 1);
        assertTrue(arr.get(0) == 1);
    }
}
