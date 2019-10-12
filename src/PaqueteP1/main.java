/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteP1;

import java.util.ArrayList;
/**
 *
 * @author victo
 */
public class main {
    
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
    
    public static void main(String[] args) {
        //Ventana v1=new Ventana(300,300);
        //v1.setVisible(true);
        Punto p1 = new Punto(0,0);
        Punto p2 = new Punto(8,8);
        Punto p3 = new Punto(-8,-8);
        Punto p4 = new Punto(8,-8);
        Punto p5 = new Punto(-8,8);
        Punto p6 = new Punto(1,2);
        Punto p7 = new Punto(4,-2);
        
        ArrayList<Punto> puntos = new ArrayList<Punto>();
        puntos.add(p1);puntos.add(p2);puntos.add(p3);puntos.add(p4);puntos.add(p5);puntos.add(p6);puntos.add(p7);
        
        Trio res = exhaustivo(puntos);
        
        res.ver();
        System.out.println("PERIMETRO: "+res.perimetro());
        
        
        
        
    }
    
    
}
