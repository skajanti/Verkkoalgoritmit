package tira.reitinhaku.algot;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import tira.reitinhaku.tirat.Keko;

public class Dijkstra {
    private double sqrt2 = Math.sqrt(2);
    /**
    * Metodi ottaa verkon ja alku- ja loppusolmun ja laskee alkusolmusta loppusolmuun lyhimmän reitin.
    * 
    * @return Metodi palauttaa IdentityHashMapin jossa on avaimena läpikäytyjä solmuja ja arvona solmun, joka on edellisenä alkusolmusta avaimena olevaan solmuun nopeimmalla reitillä.
    * 
    * @author seppo
    */
    public IdentityHashMap<Integer, Integer> hae(Verkko verkko, int alkuX, int alkuY, int loppuX, int loppuY) {
        IdentityHashMap<Integer, Integer> edellinen = new IdentityHashMap();
        Keko keko = new Keko(verkko.getX(), verkko.getY());
        
        int vX = verkko.getX();
        int vY = verkko.getY();
        keko.lisaa(alkuX, alkuY, 0.0);
        
        while (!keko.isEmpty()) {
            int solmu = keko.peekInt();
            int solmuX = solmu % vY;
            int solmuY = solmu / vY;
            double paino = keko.poll();
            if (solmuX == loppuX && solmuY == loppuY) break;
            
            
            lisaaKekoon(solmuX, solmuY, verkko, paino, edellinen, keko, loppuX, loppuY);
        }
        return edellinen;
    }
    
    public void lisaaKekoon(int solmuX, int solmuY, Verkko verkko, double paino, IdentityHashMap<Integer, Integer> edellinen, Keko keko, int loppuX, int loppuY) {
        double uusiMatka = paino + sqrt2 + oktiiliMatka(solmuX, solmuY, loppuX, loppuY);
        int vX = verkko.getX();
        int vY = verkko.getY();
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j+= 2) {
                int naapuriX = solmuX + i;
                int naapuriY = solmuY + j;
                if (verkko.onkoSolmu(naapuriX, naapuriY)) {
                    if (uusiMatka < verkko.getPaino(naapuriX, naapuriY)) {
                        verkko.setPaino(naapuriX, naapuriY, paino);
                        edellinen.put(naapuriX + vY * naapuriY, solmuX + vY * solmuY);
                        keko.lisaa(naapuriX, naapuriY, uusiMatka);
                    }
                }
            }
        }

        uusiMatka = paino + 1;
        for (int i = -1; i <= 1; i += 2) {
            int naapuriX = solmuX + i;
            int naapuriY = solmuY;
            if (verkko.onkoSolmu(naapuriX, naapuriY)) {
                if (uusiMatka < verkko.getPaino(naapuriX, naapuriY)) {
                    verkko.setPaino(naapuriX, naapuriY, paino);
                    edellinen.put(naapuriX + vY * naapuriY, solmuX + vY * solmuY);
                    keko.lisaa(naapuriX, naapuriY, uusiMatka);
                }
            }

            naapuriX = solmuX;
            naapuriY = solmuY + i;
            if (verkko.onkoSolmu(naapuriX, naapuriY)) {
                if (uusiMatka < verkko.getPaino(naapuriX, naapuriY)) {
                    verkko.setPaino(naapuriX, naapuriY, paino);
                    edellinen.put(naapuriX + vY * naapuriY, solmuX + vY * solmuY);
                    keko.lisaa(naapuriX, naapuriY, uusiMatka);
                }
            }
        }
    }
    
    public double oktiiliMatka(int x, int y, int X, int Y) {
        return 0.0;
    }
}
