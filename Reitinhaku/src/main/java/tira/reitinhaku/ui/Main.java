package tira.reitinhaku.ui;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import tira.reitinhaku.algot.AStar;
import tira.reitinhaku.algot.Dijkstra;
import tira.reitinhaku.algot.Kaari;
import tira.reitinhaku.algot.Solmu;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.utils.KartanLataaja;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> rivit = new ArrayList();
        
        KartanLataaja lataaja = new KartanLataaja();
        Verkko v = lataaja.lataa("BigGameHunters.map");
        
        AStar a = new AStar();
//        Dijkstra a = new Dijkstra();
        IdentityHashMap<Solmu, Solmu> reitti = a.hae(v, v.getSolmu(0, 30), v.getSolmu(500, 80));
        
        Solmu s = v.getSolmu(500, 80);
        int pituus = 0;
        
        while (reitti.containsKey(s)) {
            Solmu ss = reitti.get(s);
            System.out.println(ss.getX() + "," + ss.getY());
            s = ss;
            pituus++;
        }
        
        System.out.println(pituus);
        System.out.println(reitti.size());
    }
}
