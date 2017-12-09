/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Links;

import Interfaces.Avisos;
import JavaBeans.ConexaoBD;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
public class IrParaCriarBackup extends AbstractAction {

    private static final String NAME = "Criar Backup";
    private static final String ACTION = "go_criar_backup";
    private static final String SHORT_DESCRIPTION = "Crie uma cópia de segurança dos seus dados";
    private Icon SMALL_ICON = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/backup.png"));;

    public IrParaCriarBackup() {
        super();
        putValue(Action.NAME, NAME);
        putValue(Action.SHORT_DESCRIPTION, SHORT_DESCRIPTION);
        putValue(Action.ACTION_COMMAND_KEY, ACTION);
        putValue(Action.SMALL_ICON, SMALL_ICON);
    }

    public void actionPerformed(ActionEvent e) {
        ConexaoBD conexao = new ConexaoBD();
        try {
            conexao.criarBackupSql();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = formato.format(System.currentTimeMillis());
            JOptionPane.showMessageDialog(null, "Backup realizado em "+data, "SUCESSO!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(IrParaCriarBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
