/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Comun;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author victo
 */
public class generadorEtiquetas extends StandardXYItemLabelGenerator{
    private ArrayList<Vertice> vertices;
    private int modoEtiqueta; //Label encima de cada vertice: 0 = Vi(x,y), 1=Vi, 2=nada
    public generadorEtiquetas(ArrayList<Vertice> vertices, int modo)
    {
        super();
        this.vertices=vertices;
        this.modoEtiqueta=modo;
    }
    @Override
    public String generateLabel(XYDataset dataset, int series, int item)
    {
        String pattern = "###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setMaximumFractionDigits(2);
        
        double x = dataset.getXValue(series, item);
        double y = dataset.getYValue(series, item);
        
        String sx = decimalFormat.format(x);
        String sy = decimalFormat.format(y);
        
        for(Vertice v:vertices)
        {
            if(v.getX()==x && v.getY()==y)
                switch(modoEtiqueta){
                    case 0:
                        return ("V"+v.getID()+" ("+sx+" , "+sy+")");
                    case 1:
                        return ("V"+v.getID());
                    case 2:
                        return "";
                        
                }
        }
        
        return "";
    }
    
    public static void main(String[] args) {
        int contador=4; //775
        for (int i = 4; i <= 10; i++) {
//            System.out.println(i+" "+"50.0"+" "+i+".0");
//            for (int j = 10; j <= 95; j+=2) {
                System.out.println(contador+" "+i+".0"+" "+"5.0");
                contador++; 
                //System.out.println(contador+" "+(i+70)+".0"+" "+j+".0");
                //contador++;  
           // }
           
        }
    }
}
