package tira.reitinhaku.algot;

import java.util.IdentityHashMap;
import java.util.PriorityQueue;

/**
 * Lisää solmun painoon solmun ja maalin välisen oktiilimatkan, täten lähempiä solmuja priorisoidaan.
 * @author seppo
 */
public class AStar extends Dijkstra {
    private final double D = 1;
    private final double D2 = Math.sqrt(2);
    
    /**
     * Metodi palauttaa solmun painoon lisättävän heuristiikan.
     *
     * @param x Käsiteltävän solmun x-koordinaatti.
     * @param y Käsiteltävän solmun x-koordinaatti.
     * @param X Loppusolmun x-koordinaatti.
     * @param Y Loppusolmun y-koordinaatti.
     * @return Oktiilimatka käsiteltävästä solmusta kohdesolmuun.
     */
    @Override
    public double oktiiliMatka(int x, int y, int X, int Y) { 
        double dx = Math.abs(x - X);
        double dy = Math.abs(y - Y);
        return D * (dx + dy) + (D2 - 2 * D) * Math.min(dx, dy);
    }
}
