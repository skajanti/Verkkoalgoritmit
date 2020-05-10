package tira.reitinhaku.ui;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import javafx.scene.paint.Color;
import tira.reitinhaku.algot.AStar;
import tira.reitinhaku.algot.Dijkstra;
import tira.reitinhaku.algot.JumpPoint;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.tirat.Keko;
import tira.reitinhaku.utils.KartanLataaja;

public class Main {
    public static void main(String[] args) {
//        Keko keko = new Keko(10, 10);
//        for (int i = 1; i <= 10; i++) {
//            System.out.println("Lisätään: " + i);
//            keko.lisaa(i, 1, 20.0 - i);
//        }
//        keko.lisaa(5, 1, 1.0);
//        System.out.println(keko);
//        double[] arr = keko.toArray();
//        for (double i : arr) {
//            System.out.println(i);
//        }
        
        
        KartanLataaja lataaja = new KartanLataaja();
        Verkko v = lataaja.lataa("BigGameHunters.map");
        
////        AStar a = new AStar();
//        Dijkstra a = new Dijkstra();
////        JumpPoint a = new JumpPoint();
//        long alku = System.currentTimeMillis();
//        int[] reitti = a.hae(v, 0, 30, 500, 80);
//        long loppu = System.currentTimeMillis();
//        
//        int solmu = 500 + v.getY() * 80;
//        int pituus = 0;
//        
//        do {
//            int ss = reitti[solmu];
//            System.out.println(ss % v.getY() + "," + ss / v.getY());
//            solmu = ss;
//            pituus++;
//        } while (reitti[solmu] % v.getY() != 0 || reitti[solmu] / v.getY() != 30);
//        
//        System.out.println(pituus);
//        System.out.println(loppu - alku + "ms");
        
        Piirtaja p = new Piirtaja();
        p.launch();
    }
}
