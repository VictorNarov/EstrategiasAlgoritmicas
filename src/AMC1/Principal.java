package AMC1;

import java.util.*;

public class Principal {

    ArrayList<Punto> Puntos;
    
    public static Trio exhaustivo(ArrayList<Punto> vP)
    {
        
        Trio minActual = new Trio(vP.get(0), vP.get(1), vP.get(2)); //creamos un trio minimo inicial
        double dMinActual = minActual.perimetro(); //asi evitamos llamadas innecesarias a perimetro
        int i,j,k;
        
	for(i=0;i<vP.size();i++)
		for(j=i+1;j<vP.size();j++)
			for(k=j+1;k<vP.size();k++)
                        {
                            Trio temp = new Trio(vP.get(i), vP.get(j), vP.get(k));
                            if(temp.perimetro() < dMinActual)
                            {
                                minActual = temp; //reasigmanos el trio de menor perimetro
                                dMinActual = minActual.perimetro(); // actualizamod el permietro menor
                            }
                        }
			
        return minActual;
    }

    public Principal(ArrayList<Punto> Puntos) {
        Puntos = new ArrayList<>();
    }
    
    public Trio DyV(ArrayList<Punto> t, int i, int f) {
        if (f - i < 6) {
            int pmedio = (i - f) / 2;
            float dmin;
            Trio di, dd;

            di = DyV(t, i, pmedio);
            dd = DyV(t, pmedio + 1, f);

            dmin = (float) Math.min(di.perimetro(), dd.perimetro());

            //dExhaustivo=solucion del exhaustivo;
        }
        return null;
        
        
        
    }

    public float dmin(ParPuntos p1, ParPuntos p2) {
        // Cálculo de la distancia parejas de puntos
        return ((p1.getP1().getX() - p1.getP2().getX()) + (p2.getP1().getX() - p2.getP2().getX()));
    }

    /**
     * public ParPuntos dmin(ArrayList<Punto> t, int i, int f) { // Calculo del
     * primer posible par de puntos float x = (t.get(i + 1).getX() -
     * t.get(i).getX()) * (t.get(i + 1).getX() - t.get(i).getX()); float y =
     * (t.get(i + 1).getY() - t.get(i).getY()) * (t.get(i + 1).getY() -
     * t.get(i).getY());
     *
     * // Calculo de la raiz de la distancia de ambos puntos double minimo =
     * Math.sqrt(x + y);
     *
     * // Creamos un par de puntos que deberemos devolver // En caso de que
     * nuestra primera pareja sea la elegida ParPuntos devolver = new
     * ParPuntos(t.get(i).getID(), t.get(i + 1).getID(), t.get(i), t.get(i +
     * 1));
     *
     * for (int ini = i; ini < f; ini++) { float X = (t.get(ini + 1).getX() -
     * t.get(ini).getX()) * (t.get(ini + 1).getX() - t.get(ini).getX()); float Y
     * = (t.get(ini + 1).getY() - t.get(ini).getY()) * (t.get(ini + 1).getY() -
     * t.get(ini).getY()); double newmin = Math.sqrt(X + Y);
     *
     * if (newmin < minimo) { devolver = new ParPuntos(t.get(ini).getID(),
     * t.get(ini + 1).getID(), t.get(ini), t.get(ini + 1)); } }
     *
     * return devolver;
    }
     */
    public static void main(String[] args) {
        new Menu().setVisible(true);
    }
}
