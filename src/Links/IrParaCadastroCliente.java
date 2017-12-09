/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.CadastroCliente;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaCadastroCliente extends AbstractAction {

    private static final String NAME = "Cliente";
    private static final String ACTION = "go_cliente";
    private static final String SHORT_DESCRIPTION = "Cadastre um cliente";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/cliente.png"));

    

    public IrParaCadastroCliente() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);

    }

    public void actionPerformed(ActionEvent e) {
        CadastroCliente cadastroCliente = new CadastroCliente();
        cadastroCliente.setVisible(true);
    }
}
