/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Voraces;

import AMC1.Comun.Vertice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author victo
 * Grafo no dirigido con pesos
 * Implentación con listas de adyacencia
 * Esta representación consiste en n listas, 
 * de forma que la lista i-ésima contiene los
 * vértices adyacentes al vértice i
 */
public class Grafo {   
    private Map<Vertice, List<Vertice>> vAdyacentes; //lista de adyancencia
    public Set<Vertice> cjtoVertices;
    public Set<Arista> cjtoAristas;
    
    public Grafo()
    {
        this.vAdyacentes = new HashMap<>();
        this.cjtoAristas = new HashSet<>();
        this.cjtoVertices = new HashSet<>();
    }
    
    public Grafo(ArrayList<Vertice> vertices)
    {
        this.vAdyacentes = new HashMap<>();
        this.cjtoAristas = new HashSet<>();
        this.cjtoVertices = new HashSet<>();
        
        for(Vertice v:vertices)
        {
            this.anadirVertice(v);
        }
    }
    
    //public int numVertices() {return vertices.size();}
    //public boolean esVacio() {return vertices.isEmpty();}
    
    public Set<Arista> getAristas() {return this.cjtoAristas;}
    public Set<Vertice> getVertices() {return this.cjtoVertices;}
    
    public List<Vertice> getAdyacentes(Vertice v)
    {
        return vAdyacentes.get(v);
    }
    
    public void anadirVertice(Vertice v)
    {
        vAdyacentes.putIfAbsent(v, new ArrayList<>());
        cjtoVertices.add(v);
    }
    
    public void eliminarVertice(Vertice v)
    {
        //Para cada entrada del mapa (lista de adyacencia del vertice)
        //Elimina, si existe el vertice a eliminar
        vAdyacentes.values().stream().forEach(lista -> lista.remove(v)); 
 
        vAdyacentes.remove(v); //Elimina la lista de ese vertice
        cjtoVertices.remove(v); //Elimina el vertice del conjunto
        //Elimina del conjunto de aristas cada arista que tenga origen o destino V
        cjtoAristas.removeIf( (Arista a) -> a.getOrigen()== v || a.getDestino()==v);
          
    }
    
    public void anadirArista(Arista a)
    {
        
        vAdyacentes.get(a.getOrigen()).add(a.getDestino());
        vAdyacentes.get(a.getDestino()).add(a.getOrigen());
        cjtoAristas.add(a);
    }
    
    public void eliminarArista(Arista a)
    {
        //Elimina la ocurrencia de la arista en las listas de adyacencias
        List<Vertice> listaOrigen = vAdyacentes.get(a.getOrigen());
        List<Vertice> listaDestino = vAdyacentes.get(a.getDestino());
        if (listaOrigen != null)
            listaOrigen.remove(a.getOrigen());
        if (listaDestino != null)
            listaDestino.remove(a.getDestino());
        
        cjtoAristas.remove(a); //Elimina la arista del conjunto
        
    }


    
    @Override
    public String toString()
    {
        String res="";
        
        for(Map.Entry<Vertice, List<Vertice>> i : vAdyacentes.entrySet())
        {
            String adys="";
            for(Vertice vAdy : i.getValue())
            {
                adys +="\t"+vAdy.toString()+"\n";
            }
            
            res+="VERTICE "+i.getKey().toString()+" :\n\tADYACENTES\n"+adys+"\n";
            
        }
            
        
        return res;
    }
    
    public static void main(String[] args) {
        
        Vertice v1 = new Vertice(1,1.0,2.0);
        Vertice v2 = new Vertice(2,2.0,3.0);
        Vertice v3 = new Vertice(3,1.0,4.0);
        Vertice v4 = new Vertice(4,3.0,3.0);
        
        Arista a14 = new Arista(v1,v4);
        Arista a24 = new Arista(v2,v4);
        Arista a34 = new Arista(v3,v4);
        Arista a41 = new Arista(v4,v1);
        Arista a42 = new Arista(v4,v2);
        Arista a43 = new Arista(v4,v3);
        Arista a31 = new Arista(v3,v1);
        
        Grafo g = new Grafo();
        
        g.anadirVertice(v1); g.anadirVertice(v2); g.anadirVertice(v3); g.anadirVertice(v4);
        g.anadirArista(a14); 
        g.anadirArista(a24);
        g.anadirArista(a34);
        g.anadirArista(a31);
        
        
        System.out.println(g);
        System.out.println("CONJUNTO ARISTAS:\n"+g.cjtoAristas);
        g.eliminarVertice(v4);
        
        System.out.println("\nELIMINAMOS V4\n"+g);
        
        System.out.println("CONJUNTO ARISTAS:\n"+g.cjtoAristas);
    
    }
    
}
