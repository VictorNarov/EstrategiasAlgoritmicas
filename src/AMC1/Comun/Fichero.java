/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Comun;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import AMC1.Comun.Vertice;
import AMC1.Voraces.Arista;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author victo
 */
public class Fichero {

    private final Path ruta;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private ArrayList<Vertice> PuntosLeidos = new ArrayList<Vertice>();

    public Fichero(String nombreFich) {
        ruta = Paths.get(nombreFich);

    }

    public ArrayList<Vertice> getPuntos() {
        return this.PuntosLeidos;
    }

    public ArrayList<Vertice> procesar() throws IOException, Exception {
        try ( Scanner scanner = new Scanner(ruta, ENCODING.name())) {
            for (int i = 0; i < 6; i++) { //Salta las 7 primeras lineas de informacion
                scanner.nextLine();
            }
            String linea = scanner.nextLine();
            while (scanner.hasNextLine() && !linea.equals("EOF")) {
                PuntosLeidos.add(procesarLinea(linea));
                linea = scanner.nextLine();
            }
        } finally {
            return this.PuntosLeidos;
        }
    }

    protected Vertice procesarLinea(String linea) throws Exception {
        //use a second Scanner to parse the content of each line 
        try ( Scanner scanner = new Scanner(linea)) {
            scanner.useDelimiter(" ");
            if (scanner.hasNext()) {
                //assumes the line has a certain structure
                int id = Integer.parseInt(scanner.next());
                double x = Double.parseDouble(scanner.next());
                double y = Double.parseDouble(scanner.next());
                Vertice p = new Vertice(id, x, y);
                return p;

            } else {
                throw new java.lang.Exception("Error al procesar linea!");
            }

        }
    }

    public void guardarPuntos(ArrayList<Vertice> puntos) {
        try {
            FileWriter fw = new FileWriter(ruta.toFile());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);

            salida.println("NAME: "+ruta.getFileName().toString());
            salida.println("TYPE: TSP");
            salida.println("COMMENT: FICHERO GENERADO");
            salida.println("EDGE_WEIGHT_TYPE: EUC_2D");
            salida.println("DIMENSION: "+puntos.size());
            salida.println("NODE_COORD_SECTION");
            
            for (int i = 0; i < puntos.size(); i++) {
                Vertice p = puntos.get(i);
                salida.println(p.getID()+" "+p.getX()+" "+p.getY());
            }
            salida.println("EOF");
            salida.close();
            // Modo append
            bw.close();
        } catch (java.io.IOException ioex) {
        }
    }
    
    public static void guardarCamino(ArrayList<Arista> aristas, double costeTotal, Path ruta)
    {
            try {
                FileWriter fw = new FileWriter(ruta.toFile());
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida = new PrintWriter(bw);

                salida.println("NAME: "+ruta.getFileName().toString());
                salida.println("TYPE: TOUR");
                salida.println("DIMENSION: "+aristas.size());
                salida.println("SOLUTION: "+costeTotal);
                salida.println("TOUR_SECTION");

                for(Arista a : aristas){
                    salida.println(a.getOrigen().getID()+","+a.getDestino().getID());
                }

                salida.println("-1");
                salida.println("EOF");
                salida.close();
                // Modo append
                bw.close();
        } catch (java.io.IOException ioex) {
                System.out.println(ioex.getMessage());
        }
    }
}
