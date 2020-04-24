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
import tira.reitinhaku.algot.JumpPoint;
import tira.reitinhaku.algot.Kaari;
import tira.reitinhaku.algot.Solmu;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.tirat.Keko;
import tira.reitinhaku.utils.KartanLataaja;

public class Main {
    public static void main(String[] args) {
//        Keko keko = new Keko(4, 4);
//        keko.lisaa(0, 0, 5.5);
//        keko.lisaa(0, 1, 4.5);
//        keko.lisaa(0, 2, 3.5);
//        keko.lisaa(0, 3, 2.5);
//        System.out.println(keko);
//        double[] arr = keko.toArray();
//        for (double i : arr) {
//            System.out.println(i);
//        }
//        
        
        
        
        
        KartanLataaja lataaja = new KartanLataaja();
        Verkko v = lataaja.lataa("BigGameHunters.map");
        
        AStar a = new AStar();
//        Dijkstra a = new Dijkstra();
//        JumpPoint a = new JumpPoint();
        long alku = System.currentTimeMillis();
        IdentityHashMap<Integer, Integer> reitti = a.hae(v, 0, 30, 500, 80);
        long loppu = System.currentTimeMillis();
        
        System.out.println(reitti.size());
        
        int solmu = 500 + v.getY() * 80;
        int pituus = 0;
        if (reitti.containsKey(solmu)) System.out.println("debug");
        
        while (reitti.containsKey(solmu)) {
            int ss = reitti.get(solmu);
            System.out.println(ss);
            System.out.println(ss % v.getY() + "," + ss / v.getY());
            solmu = ss;
            pituus++;
        }
        
        System.out.println(pituus);
        System.out.println((double)reitti.size()/v.getSolmut().size());
        
        System.out.println(loppu - alku + "ms");
    }
}
