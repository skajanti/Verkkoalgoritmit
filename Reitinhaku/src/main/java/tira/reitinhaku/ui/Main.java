package tira.reitinhaku.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.PriorityQueue;
import tira.reitinhaku.algot.AStar;
import tira.reitinhaku.algot.Dijkstra;
import tira.reitinhaku.algot.Solmu;
import tira.reitinhaku.algot.Verkko;

public class Main {
    public static void main(String[] args) {
        Verkko v = new Verkko();
        
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                v.lisaaSolmu(new Solmu(j, i));
            }
        }
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                v.lisaaKaari(v.getSolmut().get(i * 10 + j), v.getSolmut().get(i * 10 + j + 1));
            }
        }
        
        v.lisaaKaari(v.getSolmut().get(55), v.getSolmut().get(56));
        v.lisaaKaari(v.getSolmut().get(56), v.getSolmut().get(57));
        
        for (int i = 0; i < 10; i++) {
            for (int j = 7; j < 9; j++) {
                v.lisaaKaari(v.getSolmut().get(i * 10 + j), v.getSolmut().get(i * 10 + j + 1));
            }
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                v.lisaaKaari(v.getSolmut().get(i * 10 + j), v.getSolmut().get(i * 10 + j + 10));
            }
        }
        
//        Dijkstra d = new Dijkstra();
        AStar d = new AStar();
        IdentityHashMap<Solmu, Solmu> reitti = d.hae(v, v.getSolmut().get(0), v.getSolmut().get(07));
        int matka = 0;
        Solmu a = v.getSolmut().get(57);
        while (true) {
            Solmu s = reitti.get(a);
            if (s == null) break;
            System.out.println(s.getX() + "," + s.getY());
            a = s;
            matka++;
        }
        System.out.println(matka);
        System.out.println(reitti.size());
    }
}
