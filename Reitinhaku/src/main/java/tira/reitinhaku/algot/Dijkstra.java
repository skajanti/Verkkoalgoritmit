package tira.reitinhaku.algot;

import tira.reitinhaku.tirat.Keko;

public class Dijkstra implements ReitinHakuAlgo {
    private double sqrt2 = Math.sqrt(2);
    /**
    * Metodi ottaa verkon ja alku- ja loppusolmun ja laskee alkusolmusta loppusolmuun lyhimmän reitin.
    * 
    * @return Metodi palauttaa IdentityHashMapin jossa on avaimena läpikäytyjä solmuja ja arvona solmun, joka on edellisenä alkusolmusta avaimena olevaan solmuun nopeimmalla reitillä.
    * 
    * @author seppo
    */
    
    @Override
    public int[] hae(Verkko verkko, int alkuX, int alkuY, int loppuX, int loppuY) {
        int[] edellinen = new int[verkko.getX() * verkko.getY() + 1];
        if (alkuX == 0 || alkuY == 0 || loppuX == 0 || loppuY == 0 || alkuX == verkko.getX() || alkuY == verkko.getY() || loppuX == verkko.getX() || loppuY == verkko.getY()) return edellinen;
        Keko keko = new Keko(verkko.getX(), verkko.getY());
        double[][] painot = verkko.getPainot();
        boolean[][] kartta = verkko.getBoolkartta();
        
        
        int vX = verkko.getX();
        int vY = verkko.getY();
        keko.lisaa(alkuX, alkuY, 0.0);
        
        while (!keko.isEmpty()) {
            int solmu = keko.peekInt();
            int solmuX = solmu % vY;
            int solmuY = solmu / vY;
            double paino = keko.poll();
            if (solmuX == loppuX && solmuY == loppuY) break;
            
            
            lisaaKekoon(solmuX, solmuY, painot, kartta, verkko, paino, edellinen, keko, loppuX, loppuY);
        }
        return edellinen;
    }
    
    public void lisaaKekoon(int solmuX, int solmuY, double[][] painot, boolean[][] kartta, Verkko verkko, double paino, int[] edellinen, Keko keko, int loppuX, int loppuY) {
        double uusiMatka = paino + sqrt2 + oktiiliMatka(solmuX, solmuY, loppuX, loppuY);
        int vX = verkko.getX();
        int vY = verkko.getY();
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j+= 2) {
                int naapuriX = solmuX + i;
                int naapuriY = solmuY + j;
                if (kartta[naapuriX][naapuriY]) {
                    if (uusiMatka < painot[naapuriX][naapuriY]) {
                        painot[naapuriX][naapuriY] = uusiMatka;
                        edellinen[naapuriX + vY * naapuriY] = solmuX + vY * solmuY;
                        keko.lisaa(naapuriX, naapuriY, uusiMatka);
                    }
                }
            }
        }

        uusiMatka = paino + 1 + oktiiliMatka(solmuX, solmuY, loppuX, loppuY);
        for (int i = -1; i <= 1; i += 2) {
            int naapuriX = solmuX + i;
            int naapuriY = solmuY;
            if (kartta[naapuriX][naapuriY]) {
                if (uusiMatka < painot[naapuriX][naapuriY]) {
                    painot[naapuriX][naapuriY] = uusiMatka;
                    edellinen[naapuriX + vY * naapuriY] = solmuX + vY * solmuY;
                    keko.lisaa(naapuriX, naapuriY, uusiMatka);
                }
            }

            naapuriX = solmuX;
            naapuriY = solmuY + i;
            if (kartta[naapuriX][naapuriY]) {
                if (uusiMatka < painot[naapuriX][naapuriY]) {
                    painot[naapuriX][naapuriY] = uusiMatka;
                    edellinen[naapuriX + vY * naapuriY] = solmuX + vY * solmuY;
                    keko.lisaa(naapuriX, naapuriY, uusiMatka);
                }
            }
        }
    }
    
    public double oktiiliMatka(int x, int y, int X, int Y) {
        return 0.0;
    }
}
