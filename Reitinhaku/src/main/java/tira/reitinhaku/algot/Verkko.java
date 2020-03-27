package tira.reitinhaku.algot;

import java.util.ArrayList;

/**
 * 
 * @author seppo
 */
public class Verkko {
    private ArrayList<Solmu> solmut;
    private ArrayList<Kaari> kaaret; // Saattaa olla turha
    
    private Solmu[][] kartta;
    
    public Verkko() {
        solmut = new ArrayList();
        kaaret = new ArrayList();
        kartta = null;
    }
    
    public Verkko(int x, int y) {
        solmut = new ArrayList();
        kaaret = new ArrayList();
        kartta = new Solmu[x][y];
    }
    
    public void lisaaSolmu(Solmu s) {
        solmut.add(s);
    }
    
    public boolean lisaaSolmu(Solmu s, int x, int y) {
        solmut.add(s);
        if (kartta != null) {
            kartta[x][y] = s;
            return true;
        }
        return false;
    }
    
    public void lisaaKaari(Kaari k) {
        kaaret.add(k);
    }
    
    public void lisaaKaari(Solmu a, Solmu b) {
        kaaret.add(new Kaari(a, b));
    }

    public ArrayList<Solmu> getSolmut() {
        return solmut;
    }
    
    public Solmu getSolmu(int x, int y) {
        if (kartta != null && y < kartta.length && x < kartta[0].length) return kartta[x][y];
        return null;
    }

    public ArrayList<Kaari> getKaaret() {
        return kaaret;
    }
}
