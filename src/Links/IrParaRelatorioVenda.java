/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.PesquisarCompra;
import Interfaces.VisualizarCompra;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaRelatorioVenda extends AbstractAction {
    
    private static final String NAME = "Vendas";
    private static final String ACTION = "go_vendas";
    private static final String SHORT_DESCRIPTION = "Visualizar o relatório das vendas";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/compras.png"));
    
    public IrParaRelatorioVenda() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
       VisualizarCompra visualizarCompra = new VisualizarCompra();
       visualizarCompra.setVisible(true);
    }
    
}
