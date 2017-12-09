/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.PesquisarCompra;
import java.awt.event.ActionEvent;
import java.text.ParseException;
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
public class IrParaPesquisarVenda extends AbstractAction {

    
    private static final String NAME = "Venda";
    private static final String ACTION = "go_venda";
    private static final String SHORT_DESCRIPTION = "Pesquise uma venda";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/compra.png"));

    public IrParaPesquisarVenda() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        PesquisarCompra pesquisarCompra = null;
        try {
            pesquisarCompra = new PesquisarCompra();
        } catch (ParseException ex) {
            Logger.getLogger(IrParaPesquisarVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        pesquisarCompra.setVisible(true);
    }
    
    
    
}
