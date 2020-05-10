package tira.reitinhaku.utils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import tira.reitinhaku.algot.Verkko;
import tira.reitinhaku.tirat.ExtendingList;

/**
 * Lukee MovingAI:n formaatissa olevan karttatiedoston ja tekee siitä verkon. Verkkoon lisätään pelkkää falsea olevat reunat. Tämä vähentää virheentarkastusvaatimusta.
 * 
 * @author seppo
 */
public class KartanLataaja {
    public Verkko lataa(String tiedosto) {
        ExtendingList<String> rivit = new ExtendingList();
        
        try (Scanner lukija = new Scanner(Paths.get(tiedosto))) {
            while (lukija.hasNextLine()) {
                rivit.add(lukija.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Kartanluku ei toimi.");
            return null;
        }
        
        int y = Integer.parseInt(rivit.get(1).split(" ")[1]);
        int x = Integer.parseInt(rivit.get(2).split(" ")[1]);
        
        boolean[][] kartta = new boolean[x + 2][y + 2];
        Verkko v = new Verkko(x + 2, y + 2);
        
        for (int i = 0; i < y; i++) { // luodaan solmut ja lisätään ne verkkoon
            char[] charArr = rivit.get(i + 4).toCharArray();
            for (int j = 0; j < x; j++) {
                if (charArr[j] == '.' || charArr[j] == 'G' || charArr[j] == 'S') {
                    kartta[j + 1][i + 1] = true;
                }
            }
        }
        
        v.lisaaBoolKartta(kartta);
        return v;
    }
}
