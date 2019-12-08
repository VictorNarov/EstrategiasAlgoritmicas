/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Voraces;

import AMC1.Comun.Vertice;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author victo
 */
public class AlgoritmosV {
    private Grafo g;
    
    public AlgoritmosV(Grafo g)
    {
        this.g = g;
    }
    
    public static ArrayList<Arista> Prim(Grafo g)
    {
        ArrayList<Arista> sol = new ArrayList<>(); //Conjunto de aristas solucion
        Set<Vertice> vertices = g.cjtoVertices; //Conjunto de todos los vertices del grafo
        Set<Vertice> vertices_temp = new HashSet<>(); //Conjunto de vertices vacio
        
        Map<Vertice,Boolean> visitados = new HashMap<>(); //Diccionario vertices visitados
        vertices.forEach(v -> visitados.putIfAbsent(v, false)); //Inicializamos a falso
        
        Vertice vOrigen = vertices.iterator().next(); //Cogemos un vertice cualquiera
        for(Vertice v : vertices) //Si existe el vertice 1, lo iniciamos en ese
            if(v.getID()==1)
                vOrigen=v; 
                
        
        vertices_temp.add(vOrigen); //Inicia el conjunto con un vertice cualquiera
        visitados.put(vOrigen, true);
        
        PriorityQueue<Arista> cola = new PriorityQueue<>(); //Cola de prioridad con la arista de menor coste
        while(!vertices_temp.equals(vertices)) //Mientras no esten conectados todos los vertices
        {
                for(Vertice v : g.getAdyacentes(vOrigen)) //Por cada vertice adyacente
                {
                    if(!visitados.get(v))   //Si no esta visitado
                        cola.offer(new Arista(vOrigen,v)); //Encolamos la arista
                }
                
                Arista a = cola.poll(); //Obtenemos la menor arista de la cola 
                if(!visitados.get(a.getDestino())) //Si su destino no esta visitado
                {
                    visitados.put(a.getDestino(), true);//Se marca visitado
                    vertices_temp.add(a.getDestino());  //Se añade al conjunto de vertices
                    sol.add(a);                         //Y al conjunto solucion
                    vOrigen = a.getDestino();           //El siguiente vertice en procesar será el nuevo conectado 
                }
            }
  
        return sol;
        
    }
    
    public static ArrayList<Arista> Kruskal(Grafo g, boolean imprimirDesarrollo)
    {        
        ArrayList<Arista> solucion = new ArrayList<>(); //Arbol de recubrimiento minimo solucion
        PriorityQueue<Arista> cola = new PriorityQueue<Arista> //Creamos una cola de prioridad que al añadir las aristas
            (g.cjtoVertices.size(),Comparator.comparingDouble(a -> a.getCoste())); //Las ordene por su coste
        
        for(Arista a:g.cjtoAristas)
            cola.add(a); //Añadimos todas las aristas a la cola
        
        Vertice[] conjunto = new Vertice[g.cjtoVertices.size()+1]; //Creamos un conjunto de n Vertices
        for(Vertice v:g.cjtoVertices)//Cada  posicion i indica qué vertice es padre del V con id=i
        {
            conjunto[v.getID()] = v;
        }
        
        int i=0;
        while(i < g.cjtoVertices.size()-1) //Hasta tener n-1 aristas
        {
            Arista a = cola.remove(); //Seleccionamos la arista de menor coste
            
            
            int conjuntoU = buscar(conjunto, a.getOrigen());
            int conjuntoV = buscar(conjunto, a.getDestino());
            
            if(imprimirDesarrollo){
            System.out.println("SELECCIONAMOS ARISTA: "+a);
            System.out.println("\tCONJUNTO ORIGEN: "+conjuntoU);
            System.out.println("\tCONJUNTO DESTINO: "+conjuntoV);
            }
            
            if(conjuntoU!=conjuntoV) //Si no forma ciclo
            {
                solucion.add(a);      //Añadimos al array solucion
                i++;
                fusionar(conjunto, a.getOrigen(), a.getDestino()); //Unimos sus conjuntos
                if(imprimirDesarrollo){
                System.out.println("NO FORMA CICLO");
                System.out.println("FUSIONAR "+a.getOrigen()+" U "+a.getDestino());
                }
            }
            else if(imprimirDesarrollo)
                System.out.println("FORMA CICLO -> LA DESCARTAMOS");
            
        }
        
        return solucion;
    }
    
    public static int buscar(Vertice[] conjunto, Vertice v)
    {
        if(conjunto[v.getID()] != v)
            return buscar(conjunto, conjunto[v.getID()]);
        return v.getID();
    }
    
    public static void fusionar(Vertice[] conjunto, Vertice u, Vertice v)
    {
        int conjuntoU = buscar(conjunto,u);
        int conjuntoV = buscar(conjunto,v);
        
        conjunto[conjuntoV] = u;
    }
}
