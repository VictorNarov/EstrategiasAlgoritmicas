/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.DyV;

import AMC1.Comun.Vertice;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class Desviacion {
    public static double desviacionEstandar(ArrayList<Vertice> vertices)
    {
        double media=0;
        double sumatorio=0;
        double desviacion;
        for(Vertice v:vertices)
        {
            media+=v.getX();
        }
        media/=vertices.size();
        
        for(Vertice v:vertices)
        {
            sumatorio+=Math.pow((v.getX()-media),2); //Cuadrado de la diferencia de los puntos con la media
        }
        
        desviacion = sumatorio / vertices.size(); //Media aritmetica 
        
        return Math.sqrt(desviacion); //Raiz cuadrada
    }
}
