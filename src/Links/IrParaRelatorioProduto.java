/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.VisualizarProduto;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaRelatorioProduto extends AbstractAction {

    private static final String NAME = "Produtos";
    private static final String ACTION = "go_produtos";
    private static final String SHORT_DESCRIPTION = "Visualize o relatório dos produtos cadastrados";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/produtos.png"));
    
    public IrParaRelatorioProduto() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        VisualizarProduto visualizarProduto = new VisualizarProduto();
        visualizarProduto.setVisible(true);
    }
}
