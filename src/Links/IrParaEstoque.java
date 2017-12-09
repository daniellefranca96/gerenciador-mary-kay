/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Estoque;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaEstoque extends AbstractAction {

    private static final String NAME = "Estoque";
    private static final String ACTION = "go_estoque";
    private static final String SHORT_DESCRIPTION = "Visualize o estoque";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/estoque.png"));
    
    public IrParaEstoque() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        Estoque estoque=null;
        try {
            estoque = new Estoque();
        } catch (Exception ex) {
            Logger.getLogger(IrParaEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        estoque.setVisible(true);
    }
}
