/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Avisos;
import Interfaces.MostrarCompra;
import JavaBeans.ConexaoBD;
import JavaBeans.Venda;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
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
public class IrParaVisualizarCompra extends AbstractAction {

    
    private static final String NAME = "Visualizar Venda";
    private static final String ACTION = "go_visualizar_venda";
    private static final String SHORT_DESCRIPTION = "Visualize a Venda";
    int codigo;
    
    
    public IrParaVisualizarCompra (int cod){
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        this.codigo = cod;
    }
    
    public void actionPerformed(ActionEvent e) {
        ConexaoBD conexao = new ConexaoBD();
        ArrayList v = new <Venda> ArrayList();
        try {
            v =  conexao.getVenda(1,codigo);
        } catch (ParseException ex) {
            Logger.getLogger(IrParaVisualizarCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        Venda venda = (Venda) v.get(0);
        MostrarCompra mostrarCompra = new MostrarCompra(venda);
        mostrarCompra.setVisible(true);
    }
}
