package tira.reitinhaku.algot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Solmu implements Comparator<Solmu>, Comparable<Solmu> {
    private int x;
    private int y;
    private double matka;
    private ArrayList<Kaari> kaaret;
    
    public Solmu(int x, int y) {
        this.x = x;
        this.y = y;
        matka = Double.MAX_VALUE;
        kaaret = new ArrayList();
    }
    
    public void lisaaKaari(Kaari k) {
        kaaret.add(k);
    }
    
    public void setMatka(double matka) {
        this.matka = matka;
    }

    public ArrayList<Kaari> getKaaret() {
        return kaaret;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getMatka() {
        return matka;
    }

    @Override
    public int compare(Solmu o1, Solmu o2) {
        return (int) (o1.getMatka() - o2.getMatka());
    }

    @Override
    public int compareTo(Solmu o) {
        return (int) (this.matka - o.getMatka());
    }

    @Override
    public int hashCode() { // Matka ja kaaret muuttuvat metodin käytön aikana
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) { // Matka ja kaaret muuttuvat metodin käytön aikana
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solmu other = (Solmu) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
}
