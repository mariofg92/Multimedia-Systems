/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal.clasesFormas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * Clase de forma basada en "contenedores" rectangulares (2 dimensiones). Engloba: la forma, el trazo y el color de trazo,
 * si está rellena o no y el relleno.
 * @author Mario
 */
public class FormaRectangularMFG extends FormaBasicaMFG{
    protected boolean relleno = false;
    protected Paint paint_relleno; //Puede ser liso o degradado
    
    /**
     * Crea una nueva forma rectangular.
     * @param s Forma rectagular a representar, por ej: Rectangle2D
     * @param c Color de trazo de la forma a representar.
     * @param e Trazo de la forma a representar.
     * @param r Si está o no rellena.
     * @param p Paint de relleno de la forma a representar.
     */
    public FormaRectangularMFG(Shape s, Color c, Stroke e, boolean r, Paint p){
        super(s, c, e);
        relleno = r;
        paint_relleno = p;
    }
    
    /**
     * 
     * @param b Si está o no rellena.
     */
    public void setRelleno(boolean b){
        relleno = b;
    }
    /**
     * 
     * @return Devuelve si está o no rellena.
     */
    public boolean getRelleno(){
        return relleno;
    }
    
    /**
     * 
     * @param p Paint de relleno de la forma a representar.
     */
    public void setPaintRelleno(Paint p){
        paint_relleno = p;
    }
    /**
     * 
     * @return Devuelve el paint de relleno de la forma a representar.
     */
    public Paint getPaintRelleno(){
        return paint_relleno;
    }
    
    /**
     * Pinta en un graphis la forma con sus correspondientes dimensiones,
     * posición y atributos (trazo, color de trazo y relleno en caso de tenerlo).
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D)g;
        if(relleno){
            g2d.setPaint(paint_relleno);
            g2d.fill(forma);
        }
    }
}
