package tira.reitinhaku.algot;

import java.util.ArrayList;

public class Verkko {
    private ArrayList<Solmu> solmut;
    private ArrayList<Kaari> kaaret;
    
    public Verkko() {
        solmut = new ArrayList();
        kaaret = new ArrayList();
    }
    
    public void lisaaSolmu(Solmu s) {
        solmut.add(s);
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

    public ArrayList<Kaari> getKaaret() {
        return kaaret;
    }
}
