/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Comprar;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaCadastroVenda extends AbstractAction {

    private static final String NAME = "Venda";
    private static final String ACTION = "go_venda";
    private static final String SHORT_DESCRIPTION = "Cadastre uma Venda";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/compra.png"));
    
    public IrParaCadastroVenda() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        Comprar comprar = new Comprar();
        comprar.setVisible(true);
    }
}
