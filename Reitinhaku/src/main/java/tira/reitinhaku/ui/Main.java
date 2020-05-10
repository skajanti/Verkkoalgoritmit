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
import tira.reitinhaku.algot.ReitinHakuAlgo;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.tirat.Keko;
import tira.reitinhaku.utils.KartanLataaja;

public class Main {
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        while (true) {
            System.out.println("Haluatko testata(vastaa t) vai piirtää reitin(vastaa p)?");
            String s = lukija.nextLine();
            if (s.equals("t")) {
                Verkko v;
                KartanLataaja lataaja = new KartanLataaja();
                String karttaNimi;
                while (true) {
                    System.out.println("Syötä kartan nimi: ");
                    karttaNimi = lukija.nextLine();
                    v = lataaja.lataa(karttaNimi);
                    if (v != null) break;
                }
                
                ReitinHakuAlgo algoritmi;
                while (true) {
                    System.out.println("Valitse algoritmi: Dijkstra(d), A*(A*) tai Jump Point(jp)");
                    String algoString = lukija.nextLine();
                    if (algoString.equals("d")) {
                        algoritmi = new Dijkstra();
                        break;
                    } else if (algoString.equals("A*")) {
                        algoritmi = new AStar();
                        break;
                    } else if (algoString.equals("jp")) {
                        algoritmi = new JumpPoint();
                        break;
                    } else {
                        System.out.println("Virheellinen syöte, vastaa \"d\" tai \"A*\" tai \"jp\"");
                    }
                }
                
                int aX, aY, lX, lY;
                while (true) {
                    System.out.println("Syötä lähtöpisteen x-koordinaatti: ");
                    String aXString = lukija.nextLine();
                    try {
                        aX = Integer.parseInt(aXString);
                        break;
                    } catch (Exception e) {
                        System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
                    }
                }
                
                while (true) {
                    System.out.println("Syötä lähtöpisteen y-koordinaatti: ");
                    String aYString = lukija.nextLine();
                    try {
                        aY = Integer.parseInt(aYString);
                        break;
                    } catch (Exception e) {
                        System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
                    }
                }
                
                while (true) {
                    System.out.println("Syötä maalipisteen x-koordinaatti: ");
                    String lXString = lukija.nextLine();
                    try {
                        lX = Integer.parseInt(lXString);
                        break;
                    } catch (Exception e) {
                        System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
                    }
                }
                
                while (true) {
                    System.out.println("Syötä maalipisteen y-koordinaatti: ");
                    String lYString = lukija.nextLine();
                    try {
                        lY = Integer.parseInt(lYString);
                        break;
                    } catch (Exception e) {
                        System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
                    }
                }
                
                int testeja;
                while (true) {
                    System.out.println("Kuinka monta kertaa testataan?");
                    String maaraString = lukija.nextLine();
                    try {
                        testeja = Integer.parseInt(maaraString);
                        break;
                    } catch (Exception e) {
                        System.out.println("Virheellinen syöte, täytyy olla kokonaisluku");
                    }
                }
                
                long summaAika = 0;
                for (int i = 1; i <= testeja; i++) {
                    v = lataaja.lataa(karttaNimi);
                    long alku = System.nanoTime();
                    int[] tulos = algoritmi.hae(v, aX, aY, lX, lY);
                    long loppu = System.nanoTime();
                    if (tulos[lX + lY * v.getY()] == 0) {
                        System.out.println("Ei reittiä");
                        break;
                    }
                    long aika = loppu - alku;
                    summaAika += aika;
                    System.out.println(i + " testiä suoritettu, aika: " + aika + "ns");
                    v.nollaa();
                }
                long keskiArvoAika = summaAika / testeja;
                System.out.println("Algoritmin suoritusajan keskiarvo: " + keskiArvoAika + "ns");
                break;
            } else if (s.equals("p")) {
                Piirtaja p = new Piirtaja();
                p.launch();
                break;
            } else {
                System.out.println("Virheellinen syöte, vastaa \"t\" tai \"p\"");
            }
        }
    }
}
