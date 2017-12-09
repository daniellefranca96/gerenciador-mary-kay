/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Selecionar;
import JavaBeans.ConexaoBD;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Danielle
 */
public class IrParaImportarBackup extends AbstractAction {

    private static final String NAME = "Importar Backup";
    private static final String ACTION = "go_importar_backup";
    private static final String SHORT_DESCRIPTION = "Importe uma cópia de segurança dos seus dados criada anteriormente";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/importar.png"));

    public IrParaImportarBackup() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Selecionar selecionar = new Selecionar(null, 2, "importar", null);
            selecionar.fechar();
        } catch (Exception ex) {
            Logger.getLogger(IrParaImportarBackup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
