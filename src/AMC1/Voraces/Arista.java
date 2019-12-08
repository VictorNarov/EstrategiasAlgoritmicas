/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Voraces;

import AMC1.Comun.Vertice;

/**
 *
 * @author victo
 */
public class Arista implements Comparable<Arista>{
    private Vertice origen;
    private Vertice destino;
    private final double coste;
    
    public Arista(Vertice o, Vertice d)
    {
        this.origen = o;
        this.destino = d;
        this.coste = Vertice.distanciaEuclidiana(o, d);
    }
    
    public Vertice getOrigen() {return this.origen;}
    public Vertice getDestino() {return this.destino;}
    public double getCoste() {return this.coste;}
    
    public void setOrigen(Vertice o) {this.origen=o;}
    public void setDestino(Vertice d){this.destino=d;}
    
    @Override
    public String toString()
    {
        return (origen.getID() + " -> " + destino.getID() + " COSTE: " + " ["+coste+"] \n");
    }
    
    public boolean equals(Arista a)
    {
        return (this.origen.equals(a.origen) && this.destino.equals(a.destino));
    }

    
    @Override
    public int compareTo(Arista a) { //Compara aristas segun coste
        return Double.compare(this.coste, a.coste);
        
    }
     
}
