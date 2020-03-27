package tira.reitinhaku.algot;

import java.util.IdentityHashMap;
import java.util.PriorityQueue;

/**
 * Metodi ottaa verkon ja alku- ja loppusolmun ja laskee alkusolmusta loppusolmuun lyhimmän reitin.
 * Käyttää tällä hetkellä manhattan-etäisyyttä heuristiikkana.
 * 
 * @return Metodi palauttaa IdentityHashMapin jossa on avaimena läpikäytyjä solmuja ja arvona solmun, joka on edellisenä alkusolmusta avaimena olevaan solmuun nopeimmalla reitillä.
 * 
 * @author seppo
 */
public class AStar {
    public IdentityHashMap<Solmu, Solmu> hae(Verkko verkko, Solmu alku, Solmu loppu) {
        IdentityHashMap<Solmu, Solmu> edellinen = new IdentityHashMap();
        PriorityQueue<Solmu> keko = new PriorityQueue();
        
        for (Solmu s : verkko.getSolmut()) {
            s.setMatka(Double.MAX_VALUE);
            keko.add(s);
            
        }
        alku.setMatka(0.0);
        keko.remove(alku);
        keko.add(alku);
        
        while (!keko.isEmpty()) {
            Solmu s = keko.poll();
            if (s.equals(loppu)) break;
            
            for (Kaari k : s.getKaaret()) {
                double uusiMatka = s.getMatka() + k.getPaino();
                Solmu t = k.getToinen(s);
                
                if (uusiMatka < t.getMatka()) {
                    double hMatka = Math.abs(t.getX() - loppu.getX()) + Math.abs(t.getY() - loppu.getY());
                    t.setMatka(uusiMatka + 0.5 * hMatka);
                    edellinen.put(t, s);
                    keko.remove(t);
                    keko.add(t);
                }
            }
        }
        
        return edellinen;
    }
}
