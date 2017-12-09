/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Pesquisar;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaPesquisarClienteProduto extends AbstractAction {

    private static final String NAME = "Cliente/Produto";
    private static final String ACTION = "go_cliente/produto";
    private static final String SHORT_DESCRIPTION = "Pesquise um cliente ou um produto";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/cliente_produto.png"));
    
    public IrParaPesquisarClienteProduto() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        Pesquisar pesquisar = new Pesquisar();
        pesquisar.setVisible(true);
    }
}
