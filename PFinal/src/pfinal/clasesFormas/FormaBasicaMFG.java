/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal.clasesFormas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;


/**
 * Clase de forma básica (1 sola dimensión). Engloba: la forma, el trazo y el color de trazo.
 * @author Mario
 */
public class FormaBasicaMFG{
    protected Shape forma;
    protected Stroke stroke;
    protected Color color_trazo;
    
    /**
     * Crea una nueva forma básica.
     * @param s Forma basica a representar, por ej: line2D.
     * @param c Color de trazo de la forma.
     * @param e Tipo de trazo de la forma.
     */
    public FormaBasicaMFG(Shape s, Color c, Stroke e){
        forma = s;
        color_trazo = c;
        stroke = e;
    }
    
    /**
     * 
     * @param s Forma a asignar.
     */
    public void setForma(Shape s){
        forma = s;
    }
    /**
     * 
     * @return Devuelve la forma a representar.
     */
    public Shape getForma(){
        return forma;
    }
    
    /**
     * 
     * @param s Tipo de trazo.
     */
    public void setStroke(Stroke s){
        stroke = s;
    }
    /**
     * 
     * @return Devuelve el tipo de trazo de la forma.
     */
    public Stroke getStroke(){
        return stroke;
    }
    
    /**
     * 
     * @param c Color de trazo.
     */
    public void setColorTrazo(Color c){
        color_trazo = c;
    }
    
    /**
     * 
     * @return Devuelve el color de trazo de la forma.
     */
    public Color getColorTrazo(){
        return color_trazo;
    }
    
    /**
     * Pinta en un graphis la forma con sus correspondientes dimensiones,
     * posición y atributos (trazo y color de trazo).
     * @param g Graphics sobre el que se dibujará la forma.
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(color_trazo);
        g2d.setStroke(stroke);
        g2d.draw(forma);
    }
    
}
