package tira.reitinhaku.tirat;

public class ExtendingList<T> {
    T[] arr;
    int i, size;
    
    public ExtendingList() {
        arr = (T[]) new Object[10];
        size = 10;
        i = 0;
    }
    
    public void add(T t) {
        if (i >= size) extend();
        arr[i] = t;
        i++;
    }
    
    public T get(int i) {
        return arr[i];
    }
    
    public void extend() {
        int newSize = arr.length * 2;
        T[] uusi = (T[]) new Object[newSize];
        
        for (int i = 0; i < arr.length; i++) {
            uusi[i] = arr[i];
        }
        
        arr = uusi;
        size = uusi.length;
    }
    
    public boolean isEmpty() {
        if (i == 0) return true;
        return false;
    }
    
    public int length() {
        return i;
    }
}
