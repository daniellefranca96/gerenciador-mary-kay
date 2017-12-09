/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Avisos;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaAvisos extends AbstractAction {

    
    private static final String NAME = "Avisos";
    private static final String ACTION = "go_aviso";
    private static final String SHORT_DESCRIPTION = "Visualize os avisos";
    private  Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/avisos.png"));;
    
    
    public IrParaAvisos (){
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }
    
    public void actionPerformed(ActionEvent e) {
        Avisos avisos = new Avisos();
        avisos.setVisible(true);
    }
    
    
    
}
