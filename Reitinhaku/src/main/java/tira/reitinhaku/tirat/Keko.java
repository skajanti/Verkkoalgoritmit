package tira.reitinhaku.tirat;

import java.util.Arrays;

public class Keko {
    private final int leveys, korkeus;
    private int viimeinen;
    private double[] painot;
    private int[] keko;
    
    public Keko(int x, int y) {
        viimeinen = 1;
        leveys = x;
        korkeus = y;
        painot = new double[(x + 1) * (y + 1)];
        Arrays.fill(painot, Double.MAX_VALUE);
        keko = new int[x * y + 1];
    }
    
    public void lisaa(int x, int y, double paino) {
        if (painot[x + korkeus * y] != Double.MAX_VALUE) remove(x, y);
        keko[viimeinen] = x + korkeus * y;
        painot[x + korkeus * y] = paino;
        int tama = viimeinen, ylempi = viimeinen/2;
        
        while (tama != 1) {
            double a = painot[keko[tama]], b = painot[keko[ylempi]];
            if (a < b) {
                int temp = keko[tama];
                keko[tama] = keko[ylempi];
                keko[ylempi] = temp;
            } else {
                break;
            }
            tama /= 2;
            ylempi /= 2;
        }
        
        viimeinen++;
    }
    
    public double peek() {
        return painot[keko[1]];
    }
    
    public int peekInt() {
        return keko[1];
    }
    
    public double poll() {
        double r = painot[keko[1]];
        painot[keko[1]] = Double.MAX_VALUE;
        keko[1] = keko[viimeinen - 1];
        keko[viimeinen - 1] = 0;
        
        int i = 1;
        double vanhempi = painot[keko[i]];
        double lapsi1 = painot[keko[i * 2]];
        double lapsi2 = painot[keko[i * 2 + 1]];
        while (vanhempi > lapsi1 || vanhempi > lapsi2){
            if (lapsi1 < lapsi2) {
                int temp = keko[i * 2];
                keko[i * 2] = keko[i];
                keko[i] = temp;
                i = i * 2;
            } else {
                int temp = keko[i * 2 + 1];
                keko[i * 2 + 1] = keko[i];
                keko[i] = temp;
                i = i * 2 + 1;
            }
            if (i * 2 + 1 > keko.length) break;
            vanhempi = painot[keko[i]];
            lapsi1 = painot[keko[i * 2]];
            lapsi2 = painot[keko[i * 2 + 1]];
        }
        viimeinen--;
        return r;
    }
    
    public boolean remove(int x, int y) {
        boolean r = false;
        int xy = 0;
        for (int i = 1; i <= viimeinen; i++) {
            if (keko[i] == x + korkeus * y) {
                r = true;
                xy = i;
                break;
            }
        }
        if (!r) {
            return false;
        }
        
        painot[keko[xy]] = Double.MAX_VALUE;
        keko[xy] = keko[viimeinen - 1];
        int i = xy;
        
        double vanhempi = painot[keko[i]];
        double lapsi1 = painot[keko[i * 2]];
        double lapsi2 = painot[keko[i * 2 + 1]];
        while (vanhempi > lapsi1 || vanhempi > lapsi2){
            if (lapsi1 < lapsi2) {
                int temp = keko[i * 2];
                keko[i * 2] = keko[i];
                keko[i] = temp;
                i = i * 2;
            } else {
                int temp = keko[i * 2 + 1];
                keko[i * 2 + 1] = keko[i];
                keko[i] = temp;
                i = i * 2 + 1;
            }
            if (i * 2 + 1 < keko.length) break;
            vanhempi = painot[keko[i]];
            lapsi1 = painot[keko[i * 2]];
            lapsi2 = painot[keko[i * 2 + 1]];
        }
        
        keko[viimeinen - 1] = 0;
        viimeinen--;
        return true;
    }
    
    public String toString() {
        String s = "Keko: ";
        int a = viimeinen;
        for (int i = 1; i < a; i++) {
            s += keko[i] + ", ";
        }
        
        return s;
    }
    
    /**
    * Tämä metodi on vain testaamista varten.
    */
    public double[] toArray() {
        double[] r = new double[viimeinen - 1];
        int a = viimeinen;
        for (int i = 1; i < a; i++) {
            r[i-1] = poll();
        }
        
        return r;
    }
    
    public boolean isEmpty() {
        if (viimeinen <= 1) {
            return true;
        }
        return false;
    }
}
