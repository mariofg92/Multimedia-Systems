/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import java.awt.Color;
import javax.swing.JToggleButton;

/**
 *Boton color hereda de JToggleButton pero tiene como atributo propio un color que usará como background.
 * @author Mario
 */
public class BotonColor extends JToggleButton{
    private Color color;
    
    /**
     * Constructor.
     * @param c Color propio del boton.
     */
    public BotonColor(Color c){
        color = c;
        setBackground(c);
    }
    /**
     * 
     * @param c Color a usar como color propio del botón.
     */
    public void setColor(Color c){
        color = c;
        setBackground(c);
    }
    /**
     * 
     * @return Devuelve el color propio del botón.
     */
    public Color getColor(){
        return color;
    }
}
