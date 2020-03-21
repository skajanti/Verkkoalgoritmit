package tira.reitinhaku.algot;

import java.util.ArrayList;
import java.util.HashMap;

public class BellmanFord {
    public void hae(Verkko verkko, Solmu alku, Solmu loppu) {
        ArrayList<Kaari> kaaret = verkko.getKaaret();
        ArrayList<Solmu> solmut = verkko.getSolmut();
        for (Solmu s : solmut) {
            s.setMatka(Double.MAX_VALUE);
        }
        alku.setMatka(0);
        
        while (true) {
            boolean muutos = false;
            for (Kaari k : kaaret) {
                double nyky = k.getB().getMatka();
                double uusi = k.getA().getMatka() + k.getPaino();
                
                if (uusi < nyky) {
                    k.getB().setMatka(uusi);
                    muutos = true;
                }
            }
            
            for (Kaari k : kaaret) {
                double nyky = k.getA().getMatka();
                double uusi = k.getB().getMatka() + k.getPaino();
                
                if (uusi < nyky) {
                    k.getA().setMatka(uusi);
                    muutos = true;
                }
            }
            
            if (!muutos) {
                break;
            }
        }
    }
}
