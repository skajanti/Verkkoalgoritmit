package tira.reitinhaku.algot;

import tira.reitinhaku.tirat.ExtendingList;
import tira.reitinhaku.tirat.Keko;

/**
 * Toteuttaa Juomp point searchin. Enemmän tietoa: https://zerowidth.com/2013/a-visual-explanation-of-jump-point-search.html
 * @author seppo
 */
public class JumpPoint extends AStar {
    @Override
    public void lisaaKekoon(int solmuX, int solmuY, double[][] painot, boolean[][] kartta, Verkko v, double paino, int[] edellinen, Keko keko, int loppuX, int loppuY) {
        ExtendingList<Integer> solmut = new ExtendingList();
        
        int oikea = oikea(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (oikea != -1) solmut.add(oikea);
        int vasen = vasen(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (vasen != -1) solmut.add(vasen);
        int alas = alas(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (alas != -1) solmut.add(alas);
        int ylos = ylos(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (ylos != -1) solmut.add(ylos);
        int ao = ao(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (ao != -1) solmut.add(ao);
        int av = av(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (av != -1) solmut.add(av);
        int yo = yo(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (yo != -1) solmut.add(yo);
        int yv = yv(solmuX, solmuY, painot, kartta, v, loppuX, loppuY);
        if (yv != -1) solmut.add(yv);
        
        if (solmut.isEmpty()) return;
        
        int sX, sY;
        int korkeus = v.getY();
        double sPaino;
        for (int i = 0; i < solmut.length(); i++) {
            int t = solmut.get(i);
            sX = t % korkeus;
            sY = t / korkeus;
            sPaino = v.getPaino(sX, sY);
            double uusiMatka = paino + oktiiliMatka(solmuX, solmuY, sX, sY) + oktiiliMatka(sX, sY, loppuX, loppuY);
            
            if (uusiMatka < sPaino) {
                v.setPaino(sX, sY, uusiMatka);
                edellinen[t] = solmuX + solmuY * korkeus;
//                lisaaReitti(solmuX, solmuY, sX, sY, edellinen, v.getY());
                keko.lisaa(sX, sY, uusiMatka);
            }
        }
    }
    
    public void lisaaReitti(int sX, int sY, int tX, int tY, int[] edellinen, int korkeus) {
        if (sX == tX) {
            if (sY < tY) {
                while (tY > sY) {
                    edellinen[tX + tY * korkeus] = tX + (tY - 1) * korkeus;
                    tY--;
                }
            } else {
                while (tY < sY) {
                    edellinen[tX + tY * korkeus] = tX + (tY + 1) * korkeus;
                    tY++;
                }
            }
        } else if (sY == tY) {
            if (sX < tX) {
                while (tX > sX) {
                    edellinen[tX + tY * korkeus] = tX - 1 + tY * korkeus;
                    tX--;
                }
            } else {
                while (tX < sX) {
                    edellinen[tX + tY * korkeus] = tX + 1 + tY* korkeus;
                    tX++;
                }
            }
        } else if (sX < tX){
            if (sY < tY) {
                while (tY > sY && tX > sX) {
                    edellinen[tX + tY * korkeus] = tX - 1 + (tY - 1) * korkeus;
                    tY--;
                    tX--;
                }
            } else {
                while (tY < sY) {
                    edellinen[tX + tY * korkeus] = tX - 1 + (tY + 1) * korkeus;
                    tY++;
                    tX--;
                }
            }
        } else {
            if (sY < tY) {
                while (tY > sY && tX < sX) {
                    edellinen[tX + tY * korkeus] = tX + 1 + (tY - 1) * korkeus;
                    tY--;
                    tX++;
                }
            } else {
                while (tY < sY && tX < sX) {
                    edellinen[tX + tY * korkeus] = tX + 1 + (tY + 1) * korkeus;
                    tY++;
                    tX++;
                }
            }
        }
    }
    
    public int yv(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x - i, y - i)) {
            int vasen = vasen(x - i, y - i, painot, kartta, v, loppuX, loppuY);
            int ylos = ylos(x - i, y - i, painot, kartta, v, loppuX, loppuY);
            
            if (vasen != -1 || ylos != -1) {
                return x - i + (y - i) * v.getY();
            }
            i++;
        }
        return -1;
    }
    
    public int yo(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x + i, y - i)) {
            int oikea = oikea(x + i, y - i, painot, kartta, v, loppuX, loppuY);
            int ylos = ylos(x + i, y - i, painot, kartta, v, loppuX, loppuY);
            
            if (oikea != -1 || ylos != -1) {
                return x + i + (y - i) * v.getY();
            }
            i++;
        }
        return -1;
    }
    
    public int av(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x - i, y + i)) {
            int vasen = vasen(x - i, y + i, painot, kartta, v, loppuX, loppuY);
            int alas = alas(x - i, y + i, painot, kartta, v, loppuX, loppuY);
            
            if (vasen != -1 || alas != -1) {
                return x - i + (y + i) * v.getY();
            }
            i++;
        }
        return -1;
    }

    public int ao(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (v.onkoSolmu(x + i, y + i)) {
            int oikea = oikea(x + i, y + i, painot, kartta, v, loppuX, loppuY);
            int alas = alas(x + i, y + i, painot, kartta, v, loppuX, loppuY);
            
            if (oikea != -1 || alas != -1) {
                return x + i + (y + i) * v.getY();
            }
            i++;
        }
        return -1;
    }
    
    public int ylos(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (true) {
            if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
            if (kartta[x][y - i]) {
                if ((!kartta[x + 1][y - 1] && kartta[x + 1][y - i - 1]) || (!kartta[x - 1][y - i] && kartta[x - 1][y - i - 1])) {
                    return x + (y - i) * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
    
    public int alas(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
       
        while (true) {
            if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
            if (kartta[x][y + i]) {
                if ((!kartta[x + 1][y + i] && kartta[x + 1][y + i + 1]) || (!kartta[x - 1][y + i] && kartta[x - 1][y + i + 1])) {
                    return x + (y + i) * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
    
    public int vasen(int x, int y, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (true) {
            if (x == loppuX && y == loppuY) return loppuX + loppuY * v.getY();
            if (kartta[x - i][y]) {
                if ((!kartta[x - i][y + 1] && kartta[x - i - 1][y + 1]) || (!kartta[x - i][y - 1] && kartta[x - i - 1][y - 1])) {
                    return x - i + y * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
    
    public int oikea(int solmuX, int solmuY, double[][] painot, boolean[][] kartta, Verkko v, int loppuX, int loppuY) {
        int i = 1;
        
        while (true) {
            if (solmuX == loppuX && solmuY == loppuY) return loppuX + loppuY * v.getY();
            if (kartta[solmuX + i][solmuY]) {
                if ((!kartta[solmuX + i][solmuY + 1] && kartta[solmuX + i + 1][solmuY + 1]) || (!kartta[solmuX + i][solmuY - 1] && kartta[solmuX + i + 1][solmuY - 1])) {
                    return solmuX + i + solmuY * v.getY();
                }
            } else {
                return -1;
            }
            i++;
        }
    }
}
