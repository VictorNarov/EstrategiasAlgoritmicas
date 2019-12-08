/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Comun;

/**
 *
 * @author victo
 */
public class Vertice {
    //public Vertice(int id,double px, double py) {this.id=id; this.x=px; this.y=py;}
    public Vertice(int id,double px, double py) {this.id=id; this.x=px; this.y=py;}
    public Vertice(Vertice v) {this.id=v.id; this.x=v.x; this.y=v.y;}
    private double x;
    private double y;
    private int id;
    
    public double getX() {return this.x;}
    public int getID() {return id;}
    public double getY() {return this.y;}
    public void setX(double px) {this.x=px;}
    public void setY(double py) {this.y=py;}
    
    @Override
    public String toString()
    {
       return(id+") "+ x + " - " + y+"\n");
    }
    
    
        public static double distanciaEuclidiana(Vertice p1, Vertice p2) {
        double diferenciaX = p1.getX() - p2.getX();
        double diferenciaY = p1.getY() - p2.getY();

        return (Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2)));
    }
        
    public boolean equals(Vertice p)
    {
        return (this.x == p.x && this.y==p.y);
    }
    

}
