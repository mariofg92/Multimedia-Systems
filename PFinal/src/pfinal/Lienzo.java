/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import pfinal.clasesFormas.*;

/**
 * Clase lienzo.
 * @author Mario
 */
public class Lienzo extends javax.swing.JPanel {

    private static String forma_activa = "punto"; // punto, linea, rectangulo, elipse, curva1pto
    private Point2D p1= new Point2D.Double();
    private Point2D p2 = new Point2D.Double();
    private ArrayList<FormaBasicaMFG> formas = new ArrayList(); 
    private static Color color_trazo = Color.BLACK;
    private static Color color_fondo = Color.WHITE;

    private static Stroke stroke = new BasicStroke((float) 1.0);
    private static boolean relleno = false; 
    private static int tipo_relleno = 0; //0 -> Liso, 1 -> Vertical, 2 ->Horizontal
    private static boolean editar = false;
    private static FormaBasicaMFG forma_a_editar = null;
    private boolean segundo_click = false;
    
    /**
     * Constructor de la clase.
     */
    public Lienzo() {
        initComponents();
        this.setBackground(Color.WHITE);
        
        repaint();
    }
    
    /**
     * Valores: punto, linea, rectangulo, elipse, curva1pto
     * @param forma Parametro usado para definir el tipo de forma que se dibujará.
     */
    public static void setFormaActiva(String forma){
        forma_activa = forma;
    }
    
    /**
     * 
     * @return Devuelve el tipo de forma que se está usando para dibujar en este momento.
     */
    public static String getFormaActiva(){
        return forma_activa;
    }
    
    /**
     * 
     * @param c Color a usar en el trazo.
     */
    public static void setColorTrazo(Color c){
        color_trazo = c;
    }
    
    /**
     * 
     * @param c Color a usar como secundario (fondo, degradado...).
     */
    public static void setColorFondo(Color c){
        color_fondo = c;
    }
    
    /**
     * Define si el comportamiento de edición o de creación
     * @param b Este parametro defino la edición o no edición.
     */
    public static void setEditar(boolean b){
        editar = b;
        if(forma_a_editar != null && !b){
            forma_a_editar.setColorTrazo(color_trazo);
        }
    }
    
    /**
     * 
     * @return Devuelve si se está en modo edición o no.
     */
    public static boolean getEditar(){
        return editar;
    }
    
    /**
     * 
     * @param b Define si se rellenará la figura o no en caso de ser posible.
     */
    public static void setRelleno(boolean b){
        relleno = b;
    }
    
    /**
     * 
     * @param i 0 --> relleno liso, 1 --> degradado vertical, 2 --> degradado horizontal.
     */
    public static void setTipoRelleno(int i){
        tipo_relleno = i;
    }
    
    /**
     * 
     * @param s Stroke a usar en la creación o edición de figuras.
     */
    public static void setStroke(Stroke s){
        stroke = s;
    }
    
    /**
     * 
     * @return Devuelve la figura que se está usando para editar.
     */
    public static FormaBasicaMFG getFormaAEditar(){
        return forma_a_editar;
    }
    

    /**
     * Crea una figura dependiendo de la forma_activa actual usando para ello las variables activas necesarias para cada una.
     */
    public void createForma(){
        int altura, anchura;
        Point2D min = new Point2D.Double();
        
        switch (forma_activa){
            case "punto":
                formas.add(new FormaBasicaMFG(new Line2D.Float(p1,p1), color_trazo, stroke));
                break;
            
            case "linea":
                formas.add(new FormaBasicaMFG(new Line2D.Float(p1,p2), color_trazo, stroke));
                break;
                
            case "rectangulo":
                anchura = (int)(java.lang.Math.abs(p1.getX() - p2.getX()));
                altura = (int)(java.lang.Math.abs(p1.getY() - p2.getY()));

                min.setLocation(java.lang.Math.min(p1.getX(), p2.getX()),java.lang.Math.min(p1.getY(), p2.getY()));

                formas.add(new FormaRectangularMFG(new Rectangle2D.Double(min.getX(), min.getY(), anchura, altura), color_trazo, stroke, relleno, color_fondo));
                break;
                
            case "elipse":
                anchura = (int)(java.lang.Math.abs(p1.getX() - p2.getX()));
                altura = (int)(java.lang.Math.abs(p1.getY() - p2.getY()));
                    
                min.setLocation(java.lang.Math.min(p1.getX(), p2.getX()),java.lang.Math.min(p1.getY(), p2.getY()));
                formas.add(new FormaRectangularMFG(new Ellipse2D.Double(min.getX(), min.getY(), anchura, altura), color_trazo, stroke, relleno, color_fondo));
                break;
                
            case "curva1pto":
                
                formas.add(new FormaBasicaMFG(new QuadCurve2D.Double(p1.getX(), p1.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY()), color_trazo, stroke));
                break;
        }
    }
    
    /**
     * Actualiza la figura formas[index] según los parámetros activos necesarios.
     * @param index usado para seleccionar la forma en concreto a actualizar.
     */
    public void updateForma(int index){
        int altura, anchura;
        Point2D min = new Point2D.Double();
        Shape s = formas.get(index).getForma();
        
        anchura = (int)(java.lang.Math.abs(p1.getX() - p2.getX()));
        altura = (int)(java.lang.Math.abs(p1.getY() - p2.getY()));
        min.setLocation(java.lang.Math.min(p1.getX(), p2.getX()),java.lang.Math.min(p1.getY(), p2.getY()));
        
        switch (forma_activa){
            
            case "linea":
                ((Line2D)s).setLine(p1, p2);
                break;
                
            case "rectangulo":
                ((Rectangle2D)s).setRect(min.getX(), min.getY(), anchura, altura);
                break;
                
            case "elipse":
                ((Ellipse2D)s).setFrame(min.getX(), min.getY(), anchura, altura);
                    
                break;
            case "curva1pto":
                if(segundo_click){
                    Point2D oldp1, oldp2;
                    oldp1 = ((QuadCurve2D)s).getP1();
                    oldp2 = ((QuadCurve2D)s).getP2();
                    ((QuadCurve2D)s).setCurve(oldp1,p2,oldp2);
                }else{           
                    ((QuadCurve2D)s).setCurve(p1, p1, p2);
                }
                
                break;
        }
    }
    
    /**
     * Actualiza el paint de relleno de la figura formas[index] en caso de ser posible.
     * @param index usado para seleccionar la forma en concreto a actualizar su paint.
     */
    public void updatePaint(int index){
        if (formas.get(index) instanceof FormaRectangularMFG){
            int altura, anchura;
            Point2D min = new Point2D.Double();
            FormaRectangularMFG f = (FormaRectangularMFG)formas.get(index);

            anchura = (int)(java.lang.Math.abs(p1.getX() - p2.getX()));
            altura = (int)(java.lang.Math.abs(p1.getY() - p2.getY()));
            min.setLocation(java.lang.Math.min(p1.getX(), p2.getX()),java.lang.Math.min(p1.getY(), p2.getY()));

            if(tipo_relleno == 0){
                f.setPaintRelleno(color_fondo);
            }else if(tipo_relleno == 1){
                f.setPaintRelleno(new GradientPaint((int)(min.getX() + anchura), 0, color_trazo, 0, 0, color_fondo));
            }else if(tipo_relleno == 2){
                f.setPaintRelleno(new GradientPaint(0, (int)(min.getY()+altura), color_trazo, 0, 0, color_fondo));
            }

        }
    }
    
    /**
     * Función saber si un un punto se encuentra dentro de un shape concreto.
     * @param s Shape sobre el que averiguar si es contenido el punto.
     * @param p Punto a averiguar si es contenido por la figura.
     * @return Devuelve si la figura contiene o no al punto.
     */
    public boolean contains(Shape s, Point2D p){
        boolean a_devolver = false;
        //switch s instanceof
                
        if (s instanceof Line2D){
            a_devolver = ((Line2D)s).ptLineDist(p) <= 2.0;
        }else{
            a_devolver = s.contains(p);
        }
  
        return a_devolver;
    }
    
    /**
     * 
     * @param p Punto para seleccionar la figura contenedora.
     * @return Devuelve la figura del array de formas que contiene al punto, si no hay ninguna devuelve null.
     */
    private FormaBasicaMFG getSelectedForma(Point2D p){
          for(FormaBasicaMFG f:formas)  
            if(contains(f.getForma(),p)) return f;
          return null;
    }
    
    /**
     * Función que cambia la ubicacion de una figura en concreto por la de los punto P1 y P2 actuales.
     * @param f Forma a cambiar de ubicación.
     */
    private void setLocation(FormaBasicaMFG f){ 
        double dx=p2.getX() - p1.getX();
        double dy=p2.getY() - p1.getY();
        Point2D newp1;
        Point2D newp2;
        Point2D newpc;
        Shape s = f.getForma();
        if(s instanceof Line2D){
            newp1 = new Point2D.Double(((Line2D)s).getX1()+dx, ((Line2D)s).getY1()+dy);
            newp2 = new Point2D.Double(((Line2D)s).getX2()+dx, ((Line2D)s).getY2()+dy);
            ((Line2D)s).setLine(newp1,newp2);
        }else if(s instanceof Rectangle2D){
            newp1 = new Point2D.Double(((Rectangle2D)s).getX()+dx, ((Rectangle2D)s).getY()+dy);
            ((Rectangle2D)s).setRect(newp1.getX(), newp1.getY(), ((Rectangle2D)s).getWidth(),((Rectangle2D)s).getHeight());
        }else if(s instanceof Ellipse2D){
            newp1 = new Point2D.Double(((Ellipse2D)s).getX()+dx, ((Ellipse2D)s).getY()+dy);
            Rectangle2D r = new Rectangle2D.Double(newp1.getX(), newp1.getY(), ((Ellipse2D)s).getWidth(),((Ellipse2D)s).getHeight());
            ((Ellipse2D)s).setFrame(r);
        }else if(s instanceof QuadCurve2D){
            newp1 = new Point2D.Double(((QuadCurve2D)s).getX1()+dx, ((QuadCurve2D)s).getY1()+dy);
            newp2 = new Point2D.Double(((QuadCurve2D)s).getX2()+dx, ((QuadCurve2D)s).getY2()+dy);
            newpc = new Point2D.Double(((QuadCurve2D)s).getCtrlX()+dx, ((QuadCurve2D)s).getCtrlY()+dy);
            ((QuadCurve2D)s).setCurve(newp1, newpc, newp2);
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        paintFormas(g);
    }
    
    /**
     * Pinta todas las figuras del array formas.
     * @param g 
     */
    public void paintFormas(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        for(FormaBasicaMFG f:formas) {
            f.paint(g2d);      
        }
    }
    /**
     * Función que devuelve el color complementario.
     * @param c Color del cual se quiere obtener su complementario.
     * @return Devuelve el color complementario de c.
     */
    public Color colorComplementario(Color c){
        return new Color(255- c.getRed(), 255 - c.getGreen(),255 - c.getBlue());
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función que define el comportamiento al hacer click en el lienzo.
     * Crea una nueva forma y la añade al array formas, si editar está activo selecciona la forma a editar.
     * @param evt 
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        p1.setLocation(evt.getX(), evt.getY());
        if(!editar){
            //p2 = p1;
            
                if(forma_activa.equals("curva1pto") && segundo_click){                  
                        updateForma(formas.size()-1);
                }else{
                        createForma();
                }
               
        }else{
            if(forma_a_editar != null){ //Devuelve su color a la forma seleccionada anteriormente
                forma_a_editar.setColorTrazo(color_trazo);
            }
            forma_a_editar = getSelectedForma(p1);
            if(forma_a_editar != null){
                color_trazo = forma_a_editar.getColorTrazo();
                if(forma_a_editar.getColorTrazo() == Color.BLACK){ //Para que no ponga el trazo blanco
                    forma_a_editar.setColorTrazo(Color.GREEN);
                }else{
                    forma_a_editar.setColorTrazo(colorComplementario(color_trazo));
                }
            }
        }
    }//GEN-LAST:event_formMousePressed

    /**
     * Funcion que define el comportamiento al soltar el click.
     * Coloca P2 en el punto dondo se suelta y define un comportamiento especial cuando se está creando una curva con un punto de control.
     * @param evt 
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        p2.setLocation(evt.getX(), evt.getY());
        
        if(!editar && !formas.isEmpty()){
            updateForma(formas.size()-1);
            if(forma_activa.equals("curva1pto")){
                if(!segundo_click){                    
                    segundo_click = true;
                }else{
                    segundo_click = false;
                }
            }
        }
        
        repaint();
    }//GEN-LAST:event_formMouseReleased

    /**
     * Función que define el comportamiento al arrastar un click.
     * Actualiza las dimensiones y coordenadas de la forma además de el paint para las formas con relleno degradado.
     * @param evt 
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        p2.setLocation(evt.getX(), evt.getY());
        
        if(!editar && !formas.isEmpty()){
            updateForma(formas.size()-1);
            updatePaint(formas.size()-1);
            
        }else{
            if(forma_a_editar!=null) setLocation(forma_a_editar);
            p1.setLocation(evt.getX(), evt.getY());
            
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * Función que define el comportamiento cuando el cursor entra en el lienzo.
     * Si está en modo edición cambia el cursor por HAND_CURSOR, cuando no lo está vuelve a su estado original.
     * @param evt 
     */
    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        if(editar){
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
        }
        setCursor(cursor);
    }//GEN-LAST:event_formMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
