
package business;

import javax.swing.JDialog;

/**
 *
 * @author praminda imaduwa
 */

public class Panelclose {
    
     private JDialog dg = new JDialog();
  
     public Panelclose (JDialog dgpanel){
         this.dg=dgpanel;
     }

    public JDialog getdg(){
    return this.dg;

        }

}
