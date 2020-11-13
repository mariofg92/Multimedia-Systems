/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pfinal;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase de operación de imagen propia. Su función es seleccionar una o varias bandas RGB
 * @author Mario
 */


public class BandSelectorOp  extends BufferedImageOpAdapter{
    private boolean red, green, blue;
    
    
    /**
     * Construcor de la clase.
     * 
     * @param r Selecciona o no la banda de rojo.
     * @param g Selecciona o no la banda de verde.
     * @param b Selecciona o no la banda de azul.
     */
      public BandSelectorOp(boolean r, boolean g, boolean b) {
          red = r;
          green = g;
          blue = b;
      }
      /**
       * Aplica la operación propia sobre una imagen.
       * @param src Imagen sobre la que se aplica la operación.
       * @param dest Imagen resultado, puede ser null.
       * @return Una copia del resultado de aplicar la operación sobre src.
       */
      public BufferedImage filter(BufferedImage src, BufferedImage dest){
         if (dest == null) {
            dest = createCompatibleDestImage(src, null);
         }
         
         int sample=0; 
         
         WritableRaster srcRaster = src.getRaster(); 
         WritableRaster destRaster = dest.getRaster();
         for (int row = 0; row < srcRaster.getHeight(); row++) { 
             for (int col = 0; col < srcRaster.getWidth(); col++) {
                    for (int b = 0; b < srcRaster.getNumBands(); b++) {

                        if(b==0 && red){
                            sample = srcRaster.getSample(col, row, b);
                        }else if(b==1 && green){
                            sample = srcRaster.getSample(col, row, b);
                        }else if(b==2 && blue){
                            sample = srcRaster.getSample(col, row, b);
                        }else{
                            sample = 0;
                        }
                        
                        
                        destRaster.setSample(col, row, b, sample);
            }
           }
        }
        return dest;
    
    }
}
