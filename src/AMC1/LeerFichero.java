/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
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
public class LeerFichero {

    private final Path ruta;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private ArrayList<Punto> PuntosLeidos = new ArrayList<Punto>();

    public LeerFichero(String nombreFich) {
        ruta = Paths.get(nombreFich);

    }
    public ArrayList<Punto> getPuntos (){return this.PuntosLeidos;}
    
    public void procesar() throws IOException, Exception {
        try ( Scanner scanner = new Scanner(ruta, ENCODING.name())) {
            for (int i = 0; i < 6; i++) { //Salta las 7 primeras lineas de informacion
                scanner.nextLine();
            }
            String linea = scanner.nextLine();
            while(scanner.hasNextLine() && !linea.equals("EOF"))
            {
                PuntosLeidos.add(procesarLinea(linea));
                linea = scanner.nextLine();          
            }
        }
    }
    protected Punto procesarLinea(String linea) throws Exception {
        //use a second Scanner to parse the content of each line 
        try ( Scanner scanner = new Scanner(linea)) {
            scanner.useDelimiter(" ");
            if (scanner.hasNext()) {
                //assumes the line has a certain structure
                int id = Integer.parseInt(scanner.next());
                double x = Double.parseDouble(scanner.next());
                double y = Double.parseDouble(scanner.next());
                Punto p = new Punto(id,x,y);
                return p;
                
            
          
            }else throw new java.lang.Exception("Error al procesar linea!");
                
                
            }
        }
    }
