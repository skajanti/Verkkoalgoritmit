package tira.reitinhaku.utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import tira.reitinhaku.algot.Kaari;
import tira.reitinhaku.algot.Solmu;
import tira.reitinhaku.algot.Verkko;

/**
 * Lukee MovingAI:n formaatissa olevan karttatiedoston ja tekee siitä verkon.
 * @author seppo
 */
public class KartanLataaja {
    public Verkko lataa(String tiedosto) {
        ArrayList<String> rivit = new ArrayList();
        
        try (Scanner lukija = new Scanner(Paths.get(tiedosto))) {
            while (lukija.hasNextLine()) {
                rivit.add(lukija.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Kartanluku ei toimi.");
        }
        
        int y = Integer.parseInt(rivit.get(1).split(" ")[1]);
        int x = Integer.parseInt(rivit.get(2).split(" ")[1]);
        
        boolean[][] kartta = new boolean[x][y];
        Verkko v = new Verkko(x, y);
        
        for (int i = 0; i < y; i++) { // luodaan solmut ja lisätään ne verkkoon
            for (int j = 0; j < x; j++) {
                if (rivit.get(i + 4).toCharArray()[j] == '.') {
                    kartta[j][i] = true;
                    v.lisaaSolmu(new Solmu(j, i), j, i);
                } else {
                    kartta[j][i] = false;
                }
            }
        }
        
        for (int i = 1; i < y - 1; i++) { // luodaan vierekkäisten solmujen väliin kaari (ei käsittele reunoja)
            for (int j = 1; j < x - 1; j++) {
                if (kartta[j][i]) {
                    Solmu s = v.getSolmu(j, i);
                    for (int k = -1; k <= 1; k++) {
                        if (kartta[j + k][i - 1]) {
                            Solmu ts = v.getSolmu(j + k, i - 1);
                            boolean onkojo = false;
                            
                            for (Kaari kaari : ts.getKaaret()) {
                                if (kaari.getToinen(s).equals(ts)) onkojo = true;
                            }
                            
                            if (!onkojo) {
                                v.lisaaKaari(s, ts);
                            }
                        }
                    }
                    
                    if (kartta[j - 1][i]) {
                        Solmu ts = v.getSolmu(j - 1, i);
                        boolean onkojo = false;

                        for (Kaari kaari : ts.getKaaret()) {
                            if (kaari.getToinen(s).equals(ts)) onkojo = true;
                        }

                        if (!onkojo) {
                            v.lisaaKaari(s, ts);
                        }
                    }
                    
                    if (kartta[j + 1][i]) {
                        Solmu ts = v.getSolmu(j + 1, i);
                        boolean onkojo = false;

                        for (Kaari kaari : ts.getKaaret()) {
                            if (kaari.getToinen(s).equals(ts)) onkojo = true;
                        }

                        if (!onkojo) {
                            v.lisaaKaari(s, ts);
                        }
                    }
                    
                    for (int k = -1; k <= 1; k++) {
                        if (kartta[j + k][i + 1]) {
                            Solmu ts = v.getSolmu(j + k, i + 1);
                            boolean onkojo = false;
                            
                            for (Kaari kaari : ts.getKaaret()) {
                                if (kaari.getToinen(s).equals(ts)) onkojo = true;
                            }
                            
                            if (!onkojo) {
                                v.lisaaKaari(s, ts);
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < x - 1; i++) { // käsitellään reunat vaakareunat
            if (kartta[i][0] && kartta[i + 1][0]) {
                v.lisaaKaari(v.getSolmu(i, 0), v.getSolmu(i + 1, 0));
            }
            
            if (kartta[i][y - 1] && kartta[i + 1][y - 1]) {
                v.lisaaKaari(v.getSolmu(i, y - 1), v.getSolmu(i + 1, y - 1));
            }
        }
        
        for (int i = 0; i < y - 1; i++) { // käsitellään pystyreunat
            if (kartta[0][i] && kartta[0][i + 1]) {
                v.lisaaKaari(v.getSolmu(0, i), v.getSolmu(0, i + 1));
            }
            
            if (kartta[x - 1][i] && kartta[x - 1][i + 1]) {
                v.lisaaKaari(v.getSolmu(x - 1, i), v.getSolmu(x - 1, i + 1));
            }
        }
        
        return v;
    }
}
