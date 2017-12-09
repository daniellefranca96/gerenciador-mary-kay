/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Interfaces.CadastroCliente;
import JavaBeans.ConexaoBD;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JOptionPane;
import sun.misc.Launcher;

/**
 *
 * @author Admin
 */
public class Recuperacao {

    private static String url = "jdbc:mysql://127.0.0.1:3306/";
    private static String user = "root";
    private static String senha = "W48d3o14";

    public Recuperacao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não conecto");
            Logger.getLogger(CadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recuperar() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, senha);
        Statement s = con.createStatement();
        int myResult = s.executeUpdate("CREATE DATABASE IF NOT EXISTS projetofinal;");
        s.close();
        con.close();
    }
}
