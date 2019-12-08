package AMC1.DyV;

import AMC1.Comun.Trio;
import AMC1.Comun.Vertice;
import static AMC1.Comun.Vertice.distanciaEuclidiana;
import java.util.*;
import javax.swing.JProgressBar;

public class AlgoritmosDyV {

    static ArrayList<Vertice> Puntos;

    public AlgoritmosDyV() {
    }

    ;
    
    public AlgoritmosDyV(ArrayList<Vertice> Puntos) {
        Puntos = new ArrayList<>();
    }

    public void cargarPuntos(ArrayList<Vertice> vp) {
        this.Puntos.clear();
        this.Puntos = vp;
    }

    public static Trio exhaustivo(ArrayList<Vertice> vP, int inicio, int fin) {
        Trio minActual = new Trio(vP.get(inicio), vP.get(inicio + 1), vP.get(inicio + 2)); //creamos un trio minimo inicial
        double dMinActual = minActual.getDistancia(); //asi evitamos llamadas innecesarias a perimetro
        int i, j, k;

        for (i = inicio; i <= fin; i++) {
            for (j = i + 1; j <= fin; j++) {
                for (k = j + 1; k <= fin; k++) {
                    Trio temp = new Trio(vP.get(i), vP.get(j), vP.get(k));
                    if (temp.getDistancia() < dMinActual) {
                        minActual = temp; //reasigmanos el trio de menor perimetro
                        dMinActual = minActual.getDistancia(); // actualizamod el permietro menor
                    }
                }
            }
        }

        return minActual;
    }

//    public static Trio DyV(ArrayList<Vertice> t, int i, int f) {
//        if (f - i > 6) {
//            /* Declaracion variables */
//            
//            int pmedio = (f + i + 1) / 2;           // PUNTO MEDIO
//            System.out.println("PUNTO MEDIO: "+t.get(pmedio));
//            double dmin;                        // DISTANCIA MINIMA
//            Trio solFinal;                      // SOLUCION FINAL
//
//            Trio tIzq = DyV(t, i, pmedio);      //SUBPROBLEMA MITAD-IZQDA
//            Trio tDch = DyV(t, pmedio + 1, f);  //SUBPROBLEMA MITAD-DCHA
//
//            Trio menor = menorTrioPuntos(tIzq, tDch);
//            
//
//            
//            int indiceI = pmedio;    //INDICES DEL ARRAY QUE DELIMITAN LA ZONA CENTRAL DE PUNTOS
//            while ((indiceI>0) && (Math.abs(t.get(pmedio).getX() - t.get(indiceI).getX()) <= dmin)) {
//                indiceI--;
//            }
//
//            int indiceD = pmedio;
//            while ((indiceD<t.size()-1) && (Math.abs(t.get(pmedio).getX() - t.get(indiceD).getX()) <= dmin)) {
//                indiceD++;
//            }
//
//            if (indiceD - indiceI + 1 >= 3) {
//                Trio central = exhaustivo(t, indiceI, indiceD); //HACEMOS UN CALCULO EXHAUSTIVO DE LOS PUNTOS CENTRALES
//                //Trio central = DyV(t,indiceI,indiceD);
//                solFinal = menorTrioPuntos(menor, central);      //SOLUCION FINAL: PUNTOS CON MENOR PERÍMETRO
//            } else {
//                solFinal = menor;
//            }
//
//            return solFinal;
//        } else {            //SI TENEMOS MENOS DE 6 PUNTOS, SIMPLEMENTE HACEMOS UN EXHAUSTIVO
//            return (exhaustivo(t, i, f));
//        }
//    }
    
    public static Trio DyVNuevo(ArrayList<Vertice> t, int i, int f, boolean divideX){
        if (f - i > 6) { //Si tenemos mas de 6 puntos

            int pmedio = (f + i + 1) / 2;           // PUNTO MEDIO
            //System.out.println("PUNTO MEDIO: "+t.get(pmedio));
            double dmin;                        // DISTANCIA MINIMA
            Trio solFinal;                      // SOLUCION FINAL

            Trio tIzq = DyVNuevo(t, i, pmedio, divideX);      //SUBPROBLEMA MITAD-IZQDA
            Trio tDch = DyVNuevo(t, pmedio + 1, f, divideX);  //SUBPROBLEMA MITAD-DCHA

            Trio menor = menorTrioPuntos(tIzq, tDch);
            dmin = menor.getDistancia(); ///-----------------
            
            int indiceI = pmedio;    //INDICES DEL ARRAY QUE DELIMITAN LA ZONA CENTRAL DE PUNTOS
            if(divideX)
                while ((indiceI>i) && (Math.abs(t.get(pmedio).getX() - t.get(indiceI).getX()) <= dmin)) {
                    indiceI--;
                }
            else
                while ((indiceI>i) && (Math.abs(t.get(pmedio).getY() - t.get(indiceI).getY()) <= dmin)) {
                    indiceI--;
                }

            int indiceD = pmedio+1;
            if(divideX)
                while ((indiceD<f) && (Math.abs(t.get(pmedio).getX() - t.get(indiceD).getX()) <= dmin)) {
                    indiceD++;
                }
            else
                while ((indiceD<f) && (Math.abs(t.get(pmedio).getY() - t.get(indiceD).getY()) <= dmin)) {
                    indiceD++;
                }
            
            if (indiceD - indiceI + 1 >= 3) {
                Trio central = exhaustivo(t, indiceI, indiceD); //HACEMOS UN CALCULO EXHAUSTIVO DE LOS PUNTOS CENTRALES
                //Trio central = DyV(t,indiceI,indiceD);
                solFinal = menorTrioPuntos(menor, central);      //SOLUCION FINAL: PUNTOS CON MENOR PERÍMETRO
            } else {
                solFinal = menor;
            }

            return solFinal;
        } else {            //SI TENEMOS MENOS DE 6 PUNTOS, SIMPLEMENTE HACEMOS UN EXHAUSTIVO
            return (exhaustivo(t, i, f));
        }
    }

//    private static Trio exhaustivoAntonio(ArrayList<Punto> t, int iniI, int finI, int iniD, int finD, double minimoActual) {
//        int k, m, n;
//        Trio tempI = new Trio(t.get(iniI), t.get(iniI+1), t.get(iniI + 2));
//        Trio tempD = new Trio(t.get(iniD), t.get(iniD+1), t.get(iniD + 2));
//        for (k = iniI; k <= finI; k++) { //Uno de la izquierda
//            for (m = iniD; m <= finD; m++)  //Con dos de la derecha
//                for (n = m + 1; n <= finD; n++) {
//                    tempI = new Trio(t.get(k), t.get(m), t.get(n));
//                    if (m!=n && tempI.getDistancia() < minimoActual) {
//                        minimoActual = tempI.getDistancia();
//                    }
//                }
//            }
//        
//
//        for (k = iniI; k <= finI; k++) { //Dos de la izquierda
//            for (m = k + 1; m <= finI; m++) {
//                for (n = iniD; n <= finD; n++) { //Con uno de la derecha
//                    tempD = new Trio(t.get(k), t.get(m), t.get(n));
//                    if (k!=m && tempD.getDistancia() < minimoActual) {
//                        minimoActual = tempD.getDistancia();
//                    }
//                }
//            }
//        }
//        if(tempI.getDistancia() < tempD.getDistancia())
//            return tempI;
//        else
//            return tempD;
//    }
    public static Trio menorTrioPuntos(Trio p1, Trio p2) {
        if (p1.getDistancia() < p2.getDistancia()) {
            return p1;
        } else {
            return p2;
        }
    }
        



}
