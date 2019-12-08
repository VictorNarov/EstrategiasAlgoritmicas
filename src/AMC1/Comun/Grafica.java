/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Comun;

import AMC1.Voraces.Arista;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author victo
 */
public class Grafica extends javax.swing.JPanel {

    private ArrayList<Vertice> puntos;
    private XYSeriesCollection serieXY;
    private XYSeries XYpuntos;
    private XYSeries linea1;
    private XYSeries linea2;
    private XYSeries linea3;
    private boolean haySolucion;
    private JFreeChart vista;
    private XYPlot plot;
    private XYLineAndShapeRenderer renderer;
    private ChartPanel panel;
    private int modoEtiqueta; //Modo del Label encima de cada vertice: 0 = Vi(x,y), 1=Vi, 2=nada
    private boolean ayudaActivada;//Booleano para representar o no el circulito azul de ayuda para localizar la solucion

    /**
     * Creates new form Grafica
     */
    public Grafica() {
        serieXY = new XYSeriesCollection();
        puntos = new ArrayList<Vertice>();
        XYpuntos = new XYSeries("0");
        serieXY.addSeries(XYpuntos);
        haySolucion = false;
        modoEtiqueta = 2;
        ayudaActivada = true;
        initComponents();
        
    }

    public void addPuntos(ArrayList<Vertice> puntosP) {
        puntos = puntosP;
        XYpuntos.clear();
        serieXY.removeAllSeries();

        for (Vertice v : puntos) {
            XYpuntos.add(v.getX(), v.getY());
        }
        serieXY.addSeries(XYpuntos);
        
    }
    public void limpiar()
    {
        addPuntos(new ArrayList<Vertice>());
        representa();
    }
    public void representa() {
        vista = ChartFactory.createXYLineChart(
                "",
                "",
                "",
                serieXY,
                PlotOrientation.VERTICAL,
                false,
                true,
                true
        );

        plot = (XYPlot) vista.getPlot();
        renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShape(0, new Ellipse2D.Double(-3.0, -3.0, 6, 6)); //Mostrar vertices como circulitos
        renderer.setUseOutlinePaint(true);
  
        
        //Mostrar etiquetas con informacion encima de los vertices Vi(x,y)
        renderer.setSeriesItemLabelGenerator(0, new generadorEtiquetas(puntos,modoEtiqueta));
        
                

        renderer.setDefaultItemLabelsVisible(true);

        renderer.setDefaultItemLabelFont(new Font("Dialog", 1, 12));
        
        for (int i = 1; i <=serieXY.getSeriesCount()-1 ; i++) { //Dibujar aristas

            renderer.setSeriesShape(i, new Ellipse2D.Double(-3.0, -3.0, 6, 6)); 
            renderer.setSeriesPaint(i, Color.red);
           

        }
        
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setSeriesOutlineStroke(0, new BasicStroke(1));
        renderer.setSeriesPaint(0, Color.PINK);
        
        if(ayudaActivada && linea1!=null) //Si estamos representando un trio solucion, ayudaremos a su localizacion con una elipse azul si esta activado
        {
            double minX = Math.min(Math.min(linea1.getMinX(), linea2.getMinX()), linea3.getMinX());
            double maxX = Math.max(Math.max(linea1.getMaxX(), linea2.getMaxX()), linea3.getMaxX());
            double minY = Math.min(Math.min(linea1.getMinY(), linea2.getMinY()), linea3.getMinY());
            double maxY = Math.max(Math.max(linea1.getMaxY(), linea2.getMaxY()), linea3.getMaxY());
            double plotMinX = plot.getDomainAxis().getLowerBound(); 
            double plotMaxX = plot.getDomainAxis().getUpperBound(); 

            
            Ellipse2D.Double circulo = new Ellipse2D.Double((minX+maxX)/2-(plotMaxX-plotMinX)/8, (minY+maxY)/2-(plotMaxX-plotMinX)/8, (plotMaxX-plotMinX)/4, (plotMaxX-plotMinX)/4);
            plot.addAnnotation(new XYShapeAnnotation(circulo, new BasicStroke(2.0f), Color.blue));
           

        }
   
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        //renderer.setItemLabelsVisible(true);

        plot.setRenderer(renderer);
        // Mostramos la grafica dentro del jPanel
        panel = new ChartPanel(vista);
        //panel.setSize(this.getSize());
        //panel.setSize(this.getParent().getSize());
        this.removeAll();

        this.setLayout(new BorderLayout());
        this.add(panel);
        this.validate();
        linea1=null;
        
    }
    public void addAristas(Set<Arista> aristas)
    {
        serieXY.removeAllSeries(); //Borramos por si tiene otro conjunto de aristas
        serieXY.addSeries(XYpuntos); //Añadimos los vertices de nuevo
        
        aristas.forEach((Arista a) ->
        {
            XYSeries linea = new XYSeries("A"+a.getOrigen().getID()+"-"+a.getDestino().getID()); //Nombre de la arista
            linea.add(a.getOrigen().getX(), a.getOrigen().getY());     
            linea.add(a.getDestino().getX(), a.getDestino().getY());
                        
            serieXY.addSeries(linea);
            
        });    
    }
        public void addAristas(ArrayList<Arista> aristas) //Para representar soluciones greedy
    {
        serieXY.removeAllSeries(); //Borramos por si tiene otro conjunto de aristas
        serieXY.addSeries(XYpuntos); //Añadimos los vertices de nuevo
        
        aristas.forEach((Arista a) ->
        {
            XYSeries linea = new XYSeries("A"+a.getOrigen().getID()+"-"+a.getDestino().getID()); //Nombre de la arista
            linea.add(a.getOrigen().getX(), a.getOrigen().getY());     
            linea.add(a.getDestino().getX(), a.getDestino().getY());
            
            serieXY.addSeries(linea);
            
        });    
         
    }
    public void representaSol(Trio trio) {
        haySolucion = true;
        linea1 = new XYSeries("l1");
        linea2 = new XYSeries("l2");
        linea3 = new XYSeries("l3");
        

        linea1.add(trio.getP1().getX(), trio.getP1().getY());
        linea1.add(trio.getP2().getX(), trio.getP2().getY());

        linea2.add(trio.getP1().getX(), trio.getP1().getY());
        linea2.add(trio.getP3().getX(), trio.getP3().getY());

        linea3.add(trio.getP3().getX(), trio.getP3().getY());
        linea3.add(trio.getP2().getX(), trio.getP2().getY());

        XYSeries puntos_new = serieXY.getSeries(0);
        serieXY.removeAllSeries();
        serieXY.addSeries(puntos_new);
        serieXY.addSeries(linea1);
        serieXY.addSeries(linea2);
        serieXY.addSeries(linea3);

        representa();
    }
    
    public void setModoEtiqueta(int modo)
    {
        this.modoEtiqueta = modo;
        this.representa();
    }
    public void setAyuda(boolean a)
    {
        this.ayudaActivada=a;
        representa();
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        ArrayList<Vertice> puntos = new ArrayList<Vertice>();
        puntos.add(new Vertice(1, 0, 0));
        puntos.add(new Vertice(2, 1, 1));
        puntos.add(new Vertice(3, -1, -1));

        JFrame frame = new JFrame();

        Grafica g = new Grafica();

        g.addPuntos(puntos);
        g.representa();

        frame.setSize(600, 600);
        frame.add(g);
        frame.setVisible(true);

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
