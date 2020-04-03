package tira.reitinhaku.algot;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.PriorityQueue;

/**
 *
 * @author seppo
 */
public class JumpPoint extends AStar {
    @Override
    public void lisaaKekoon(Solmu s, PriorityQueue keko, Verkko v, IdentityHashMap edellinen, Solmu loppu) {
        ArrayList<Solmu> solmut = new ArrayList();
        
        Solmu oikea = oikea(s, v, loppu);
        if (oikea != null) solmut.add(oikea);
        Solmu vasen = vasen(s, v, loppu);
        if (vasen != null) solmut.add(vasen);
        Solmu alas = alas(s, v, loppu);
        if (alas != null) solmut.add(alas);
        Solmu ylos = ylos(s, v, loppu);
        if (ylos != null) solmut.add(ylos);
        Solmu ao = ao(s, v, loppu);
        if (ao != null) solmut.add(ao);
        Solmu av = av(s, v, loppu);
        if (av != null) solmut.add(av);
        Solmu yo = yo(s, v, loppu);
        if (yo != null) solmut.add(yo);
        Solmu yv = yv(s, v, loppu);
        if (yv != null) solmut.add(yv);
        
        if (solmut.isEmpty()) return;
        
        for (Solmu t : solmut) {
            double uusiMatka = s.getMatka() + oktiiliMatka(s.getX(), s.getY(), t.getX(), t.getY());
            
            if (uusiMatka < t.getMatka()) {
                t.setMatka(uusiMatka + oktiiliMatka(t.getX(), t.getY(), loppu.getX(), loppu.getY()));
                edellinen.put(t, s);
                keko.remove(t);
                keko.add(t);
            }
        }
    }
    
    public Solmu yv(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        while (v.onkoSolmu(x - i, y - i)) {
            Solmu vasen = vasen(v.getSolmu(x - i, y - i), v, loppu);
            Solmu ylos = ylos(v.getSolmu(x - i, y - i), v, loppu);
            
            if (vasen != null || ylos != null) {
                return v.getSolmu(x - i, y - i);
            }
            i++;
        }
        return null;
    }
    
    public Solmu yo(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        while (v.onkoSolmu(x + i, y - i)) {
            Solmu oikea = oikea(v.getSolmu(x + i, y - i), v, loppu);
            Solmu ylos = ylos(v.getSolmu(x + i, y - i), v, loppu);
            
            if (oikea != null || ylos != null) {
                return v.getSolmu(x + i, y - i);
            }
            i++;
        }
        return null;
    }
    
    public Solmu av(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        while (v.onkoSolmu(x - i, y + i)) {
            Solmu vasen = vasen(v.getSolmu(x - i, y + i), v, loppu);
            Solmu alas = alas(v.getSolmu(x - i, y + i), v, loppu);
            
            if (vasen != null || alas != null) {
                return v.getSolmu(x - i, y + i);
            }
            i++;
        }
        return null;
    }

    public Solmu ao(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        while (v.onkoSolmu(x + i, y + i)) {
            Solmu oikea = oikea(v.getSolmu(x + i, y + i), v, loppu);
            Solmu alas = alas(v.getSolmu(x + i, y + i), v, loppu);
            
            if (oikea != null || alas != null) {
                return v.getSolmu(x + i, y + i);
            }
            i++;
        }
        return null;
    }
    
    public Solmu ylos(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        if (x == v.getX()) {
            while (true) {
                if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x, y - i)) {
                    if (!v.onkoSolmu(x - 1, y - i) && v.onkoSolmu(x - 1, y - i - 1)) {
                        return v.getSolmu(x, y - i);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        if (x == 1) {
            while (true) {
                if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x, y - i)) {
                    if (!v.onkoSolmu(x + 1, y - i) && v.onkoSolmu(x + 1, y - i - 1)) {
                        return v.getSolmu(x, y - i);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppux && y == loppuy) return loppu;
            if (v.onkoSolmu(x, y - i)) {
                if ((!v.onkoSolmu(x + 1, y - i) || !v.onkoSolmu(x - 1, y - i)) && (v.onkoSolmu(x + 1, y - i - 1) || v.onkoSolmu(x - 1, y - i - 1))) {
                    return v.getSolmu(x, y - i);
                }
            } else {
                return null;
            }
            i++;
        }
    }
    
    public Solmu alas(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        if (x == v.getX()) {
            while (true) {
            if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x, y + i)) {
                    if (!v.onkoSolmu(x - 1, y + i) && v.onkoSolmu(x - 1, y + i + 1)) {
                        return v.getSolmu(x, y + i);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        if (x == 1) {
            while (true) {
            if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x, y + i)) {
                    if (!v.onkoSolmu(x + 1, y + i) && v.onkoSolmu(x + 1, y + i + 1)) {
                        return v.getSolmu(x, y + i);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppux && y == loppuy) return loppu;
            if (v.onkoSolmu(x, y + i)) {
                if ((!v.onkoSolmu(x + 1, y + i) || !v.onkoSolmu(x - 1, y + i)) && (v.onkoSolmu(x + 1, y + i + 1) || v.onkoSolmu(x - 1, y + i + 1))) {
                    return v.getSolmu(x, y + i);
                }
            } else {
                return null;
            }
            i++;
        }
    }
    
    public Solmu vasen(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        if (y == v.getY()) {
            while (true) {
            if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x - i, y)) {
                    if (!v.onkoSolmu(x - i, y - 1) && v.onkoSolmu(x - i - 1, y - 1)) {
                        return v.getSolmu(x - i, y);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        if (y == 1) {
            while (true) {
            if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x - i, y)) {
                    if (!v.onkoSolmu(x - i, y + 1) && v.onkoSolmu(x - i - 1, y + 1)) {
                        return v.getSolmu(x - i, y);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppux && y == loppuy) return loppu;
            if (v.onkoSolmu(x - i, y)) {
                if ((!v.onkoSolmu(x - i, y + 1) || !v.onkoSolmu(x - i, y - 1)) && (v.onkoSolmu(x - i - 1, y + 1) || v.onkoSolmu(x - i - 1, y - 1))) {
                    return v.getSolmu(x - i, y);
                }
            } else {
                return null;
            }
            i++;
        }
    }
    
    public Solmu oikea(Solmu s, Verkko v, Solmu loppu) {
        int loppux = loppu.getX();
        int loppuy = loppu.getY();
        int x = s.getX();
        int y = s.getY();
        int i = 1;
        
        if (y == v.getY()) {
            while (true) {
            if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x + i, y)) {
                    if (!v.onkoSolmu(x + i, y - 1) && v.onkoSolmu(x + i + 1, y - 1)) {
                        return v.getSolmu(x + i, y);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        if (y == 1) {
            while (true) {
            if (x == loppux && y == loppuy) return loppu;
                if (v.onkoSolmu(x + i, y)) {
                    if (!v.onkoSolmu(x + i, y + 1) && v.onkoSolmu(x + i + 1, y + 1)) {
                        return v.getSolmu(x + i, y);
                    }
                } else {
                    return null;
                }
                i++;
            }
        }
        
        while (true) {
            if (x == loppux && y == loppuy) return loppu;
            if (v.onkoSolmu(x + i, y)) {
                if ((!v.onkoSolmu(x + i, y + 1) || !v.onkoSolmu(x + 1, y - 1)) && (v.onkoSolmu(x + i + 1, y + 1) || v.onkoSolmu(x + i + 1, y - 1))) {
                    return v.getSolmu(x + i, y);
                }
            } else {
                return null;
            }
            i++;
        }
    }
}
