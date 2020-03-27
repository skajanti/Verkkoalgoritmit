package tira.reitinhaku.algot;

/**
 * Kaari on kaksisuuntainen.
 * @author seppo
 */
public class Kaari {
    private Solmu a;
    private Solmu b;
    private double paino;
    
    public Kaari(Solmu a, Solmu b) {
        this.a = a;
        this.b = b;
        
        a.lisaaKaari(this);
        b.lisaaKaari(this);
        
        paino = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public Solmu getA() {
        return a;
    }

    public Solmu getB() {
        return b;
    }

    public double getPaino() {
        return paino;
    }
    
    public Solmu getToinen(Solmu s) {
        if (s.equals(a)) {
            return b;
        } else {
            return a;
        }
    }
}
