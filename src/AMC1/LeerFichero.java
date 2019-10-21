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
import java.util.Scanner;

/**
 *
 * @author victo
 */
public class LeerFichero {

    private final Path ruta;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    public LeerFichero(String nombreFich) {
        ruta = Paths.get(nombreFich);

    }

    public void procesar() throws IOException {
        try ( Scanner scanner = new Scanner(ruta, ENCODING.name())) {
            for (int i = 0; i < 6; i++) { //Salta las 7 primeras lineas de informacion
                scanner.nextLine();
            }
            String linea = scanner.nextLine();
            while(scanner.hasNextLine() && !linea.equals("EOF"))
            {
                procesarLinea(linea);
                linea = scanner.nextLine();          
            }
        }
    }
    protected void procesarLinea(String linea) {
        //use a second Scanner to parse the content of each line 
        try ( Scanner scanner = new Scanner(linea)) {
            scanner.useDelimiter(" ");
            if (scanner.hasNext()) {
                //assumes the line has a certain structure
                int id = Integer.parseInt(scanner.next());
                double x = Double.parseDouble(scanner.next());
                double y = Double.parseDouble(scanner.next());
                
                System.out.println("ID: "+id+" "+x+" "+y);
            } else {
                System.out.println("Error al procesar linea!");
            }
        }
    }
}