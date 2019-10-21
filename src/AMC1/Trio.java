/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1;
import java.lang.Math;

/**
 *
 * @author victo
 */
public class Trio {
    public Trio(Punto p1, Punto p2, Punto p3) {this.p1=p1; this.p2=p2; this.p3=p3;}
    
    private Punto p1;
    private Punto p2;
    private Punto p3;
    
    public Punto getP1() {return p1;}
    public Punto getP2() {return p2;}
    public Punto getP3() {return p3;}
    
    public void setP1(Punto pP1) {this.p1=pP1;}
    public void setP2(Punto pP2) {this.p2=pP2;}
    public void setP3(Punto pP3) {this.p3=pP3;}
    
    public double perimetro()
    {
        double d12 = Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(),2));
        double d13 = Math.sqrt(Math.pow(p1.getX()-p3.getX(),2)+Math.pow(p1.getY()-p3.getY(),2));
        double d23 = Math.sqrt(Math.pow(p2.getX()-p3.getX(),2)+Math.pow(p2.getY()-p3.getY(),2));
        
        return (d12+d13+d23);
    }
    
    public void ver()
    {
        p1.ver();
        p2.ver();
        p3.ver();
    }
}
