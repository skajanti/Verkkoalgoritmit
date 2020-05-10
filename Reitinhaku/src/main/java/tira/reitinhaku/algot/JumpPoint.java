package tira.reitinhaku.algot;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.PriorityQueue;
import tira.reitinhaku.tirat.Keko;

/**
 *
 * @author seppo
 */
public class JumpPoint extends AStar {
    @Override
    public void lisaaKekoon(int solmuX, int solmuY, Verkko v, double paino, int[] edellinen, Keko keko, int loppuX, int loppuY) {
        ArrayList<Integer> solmut = new ArrayList();
        
        int oikea = oikea(solmuX, solmuY, v, loppuX, loppuY);
        if (oikea != -1) solmut.add(oikea);
        int vasen = vasen(solmuX, solmuY, v, loppuX, loppuY);
        if (vasen != -1) solmut.add(vasen);
        int alas = alas(solmuX, solmuY, v, loppuX, loppuY);
        if (alas != -1) solmut.add(alas);
        int ylos = ylos(solmuX, solmuY, v, loppuX, loppuY);
        if (ylos != -1) solmut.add(ylos);
        int ao = ao(solmuX, solmuY, v, loppuX, loppuY);
        if (ao != -1) solmut.add(ao);
        int av = av(solmuX, solmuY, v, loppuX, loppuY);
        if (av != -1) solmut.add(av);
        int yo = yo(solmuX, solmuY, v, loppuX, loppuY);
        if (yo != -1) solmut.add(yo);
        int yv = yv(solmuX, solmuY, v, loppuX, loppuY);
        if (yv != -1) solmut.add(yv);
        
        if (solmut.isEmpty()) return;
        
        int sX, sY;
        int korkeus = v.getY();
        double sPaino;
        for (int t : solmut) {
            sX = t % korkeus;
            sY = t / korkeus;
            sPaino = v.getPaino(sX, sY);
            double uusiMatka = paino + oktiiliMatka(solmuX, solmuY, sX, sY) + oktiiliMatka(sX, sY, loppuX, loppuY);
            
            if (uusiMatka < sPaino) {
                v.setPaino(sX, sY, uusiMatka);
                edellinen[t] = solmuX + solmuY * korkeus;
                keko.lisaa(sX, sY, uusiMatka);
            }
        }
    }
    
    public int yv(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x - i, y - i)) {
            int vasen = vasen(x - i, y - i, v, loppuX, loppuY);
            int ylos = ylos(x - i, y - i, v, loppuX, loppuY);
            
            if (vasen != -1 || ylos != -1) {
                return x - i + (y - i) * v.getY();
            }
            i++;
        }
        return -1;
    }
    
    public int yo(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x + i, y - i)) {
            int oikea = oikea(x + i, y - i, v, loppuX, loppuY);
            int ylos = ylos(x + i, y - i, v, loppuX, loppuY);
            
            if (oikea != -1 || ylos != -1) {
                return x + i + (y - i) * v.getY();
            }
            i++;
        }
        return -1;
    }
    
    public int av(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x - i, y + i)) {
            int vasen = vasen(x - i, y + i, v, loppuX, loppuY);
            int alas = alas(x - i, y + i, v, loppuX, loppuY);
            
            if (vasen != -1 || alas != -1) {
                return x - i + (y + i) * v.getY();
            }
            i++;
        }
        return -1;
    }

    public int ao(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x + i, y + i)) {
            int oikea = oikea(x + i, y + i, v, loppuX, loppuY);
            int alas = alas(x + i, y + i, v, loppuX, loppuY);
            
            if (oikea != -1 || alas != -1) {
                return x + i + (y + i) * v.getY();
            }
            i++;
        }
        return -1;
    }
    
    public int ylos(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        if (x == v.getX()) {
            while (true) {
                if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(x, y - i)) {
                    if (!v.onkoSolmu(x - 1, y - i) && v.onkoSolmu(x - 1, y - i - 1)) {
                        return x + (y - i) * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        if (x == 1) {
            while (true) {
                if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(x, y - i)) {
                    if (!v.onkoSolmu(x + 1, y - i) && v.onkoSolmu(x + 1, y - i - 1)) {
                        return x + (y - i) * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
            if (v.onkoSolmu(x, y - i)) {
                if ((!v.onkoSolmu(x + 1, y - i) || !v.onkoSolmu(x - 1, y - i)) && (v.onkoSolmu(x + 1, y - i - 1) || v.onkoSolmu(x - 1, y - i - 1))) {
                    return x + (y - i) * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
    
    public int alas(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        if (x == v.getX()) {
            while (true) {
                if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(x, y + i)) {
                    if (!v.onkoSolmu(x - 1, y + i) && v.onkoSolmu(x - 1, y + i + 1)) {
                        return x + (y + i) * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        if (x == 1) {
            while (true) {
                if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(x, y + i)) {
                    if (!v.onkoSolmu(x + 1, y + i) && v.onkoSolmu(x + 1, y + i + 1)) {
                        return x + (y + i) * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
            if (v.onkoSolmu(x, y + i)) {
                if ((!v.onkoSolmu(x + 1, y + i) || !v.onkoSolmu(x - 1, y + i)) && (v.onkoSolmu(x + 1, y + i + 1) || v.onkoSolmu(x - 1, y + i + 1))) {
                    return x + (y + i) * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
    
    public int vasen(int x, int y, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        if (y == v.getY()) {
            while (true) {
                if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(x - i, y)) {
                    if (!v.onkoSolmu(x - i, y - 1) && v.onkoSolmu(x - i - 1, y - 1)) {
                        return x - i + y * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        if (y == 1) {
            while (true) {
                if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(x - i, y)) {
                    if (!v.onkoSolmu(x - i, y + 1) && v.onkoSolmu(x - i - 1, y + 1)) {
                        return x - i + y * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
            if (v.onkoSolmu(x - i, y)) {
                if ((!v.onkoSolmu(x - i, y + 1) || !v.onkoSolmu(x - i, y - 1)) && (v.onkoSolmu(x - i - 1, y + 1) || v.onkoSolmu(x - i - 1, y - 1))) {
                    return x - i + y * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
    
    public int oikea(int solmuX, int solmuY, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        if (solmuY == v.getY()) {
            while (true) {
                if (solmuX == loppuX && solmuY == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(solmuX + i, solmuY)) {
                    if (!v.onkoSolmu(solmuX + i, solmuY - 1) && v.onkoSolmu(solmuX + i + 1, solmuY - 1)) {
                        return solmuX + i + solmuY * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        if (solmuY == 1) {
            while (true) {
                if (solmuX == loppuX && solmuY == loppuY) return loppuX + loppuY * v.getY();
                if (v.onkoSolmu(solmuX + i, solmuY)) {
                    if (!v.onkoSolmu(solmuX + i, solmuY + 1) && v.onkoSolmu(solmuX + i + 1, solmuY + 1)) {
                        return solmuX + i + solmuY * v.getY();
                    }
                } else {
                    return -1;
                }
                i++;
            }
        }
        
        while (true) {
            if (solmuX == loppuX && solmuY == loppuY) return loppuX + loppuY * v.getY();
            if (v.onkoSolmu(solmuX + i, solmuY)) {
                if ((!v.onkoSolmu(solmuX + i, solmuY + 1) || !v.onkoSolmu(solmuX + 1, solmuY - 1)) && (v.onkoSolmu(solmuX + i + 1, solmuY + 1) || v.onkoSolmu(solmuX + i + 1, solmuY - 1))) {
                    return solmuX + i + solmuY * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
}
