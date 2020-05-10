package tira.reitinhaku.algot;

import java.util.Arrays;

/**
 * 
 * @author seppo
 */
public class Verkko {
    private double[][] painot;
    private boolean[][] boolkartta;
    private int x;
    private int y;
    
    public Verkko(int x, int y) {
        painot = new double[x][y];
        for (double[] rivi : painot) {
            Arrays.fill(rivi, Double.MAX_VALUE);
        }
        boolkartta = new boolean[x][y];
        this.x = x;
        this.y = y;
    }
    
    public void nollaa() {
        for (double[] rivi : painot) {
            Arrays.fill(rivi, Double.MAX_VALUE);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void lisaaBoolKartta(boolean[][] b) {
        boolkartta = b;
    }
    
    public double getPaino(int x, int y) {
        if (onkoSolmu(x, y)) return painot[x][y];
        return Double.MAX_VALUE;
    }
    
    public double[][] getPainot() {
        return painot;
    }
    
    public void setPaino(int x, int y, double p) {
        painot[x][y] = p;
    }
   
    public boolean[][] getBoolkartta() {
        return boolkartta;
    }
   
    public boolean onkoSolmu(int x, int y) {
        if (x >= this.x || y >= this.y || x < 0 || y < 0) return false;
        return boolkartta[x][y];
    }
}
