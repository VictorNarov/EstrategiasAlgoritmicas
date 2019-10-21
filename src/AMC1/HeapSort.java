package AMC1;
import java.util.*;

public class HeapSort {

    public void ordenar(ArrayList<Punto> t) {
        int n = t.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            amontonar(t, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Punto temp = t.get(0);

            t.set(0, t.get(i));
            t.set(i, temp);

            amontonar(t, i, 0);
        }
    }

    public void amontonar(ArrayList<Punto> t, int n, int i) {
        int mayor = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if ((l < n) && (t.get(l).getX() > t.get(mayor).getX())) {
            mayor = l;
        }

        if ((r < n) && (t.get(r).getX() > t.get(mayor).getX())) {
            mayor = r;
        }
        
        if (mayor != i) {
            Punto temp = t.get(i);
            
            t.set(i, t.get(mayor));
            t.set(mayor, temp);
            
            amontonar(t, n, mayor);
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Punto> p = new ArrayList<>();
        
        p.add(new Punto(4, (float)99.1,(float)5.1));
        p.add(new Punto(1, (float)2.1,(float)2.1));
        p.add(new Punto(2, (float)44.1,(float)3.1));
        p.add(new Punto(5, (float)6.1,(float)6.1));
        p.add(new Punto(3, (float)555.1,(float)4.1));
        p.add(new Punto(0, (float)1.1,(float)1.1));
        
        int n = p.size();
        
        HeapSort ob = new HeapSort(); 
        ob.ordenar(p); 
  
        System.out.println("Sorted array is"); 
        //printArray(arr);
        
        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getX());
        }

    }
}
