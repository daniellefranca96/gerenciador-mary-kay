/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.VisualizarCliente;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
public class IrParaRelatorioCliente extends AbstractAction {
    
    private static final String NAME = "Clientes";
    private static final String ACTION = "go_clientes";
    private static final String SHORT_DESCRIPTION = "Visualizar o relatório dos clientes";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/clientes.png"));
   
    public IrParaRelatorioCliente() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
       
    }

    public void actionPerformed(ActionEvent e) {
        VisualizarCliente visualizarCliente = new VisualizarCliente();
        visualizarCliente.setVisible(true);
    }
    
}
