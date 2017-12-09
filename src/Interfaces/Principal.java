/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Links.IrParaAvisos;
import Links.IrParaCadastroCliente;
import Links.IrParaCadastroVenda;
import Links.IrParaCadastroProduto;
import Links.IrParaConfiguracoes;
import Links.IrParaCriarBackup;
import Links.IrParaEstoque;
import Links.IrParaImportarBackup;
import Links.IrParaPesquisarClienteProduto;
import Links.IrParaPesquisarVenda;
import Links.IrParaRelatorioCliente;
import Links.IrParaRelatorioVenda;
import Links.IrParaRelatorioProduto;
import Links.IrParaSair;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jdesktop.swingx.VerticalLayout;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author Danielle
 */
public class Principal extends JFrame {

    private Container telaPrincipal;
    private ImageIcon iconeCadastrar, iconePesquisar, iconeRelatorio, iconeVisualizar, iconeSistema;
    JXTaskPaneContainer taskPaneContainer;
    JXTaskPane tpCadastrar;
    JXTaskPane tpRelatorio;
    JXTaskPane tpVisualizar;
    JXTaskPane tpPesquisar;
    JXTaskPane tpSistema;
    JLabel titulo;
    ImageIcon image;
    JLabel logo;

    public Principal() throws InstantiationException, IllegalAccessException {
        this.telaPrincipal = getContentPane();
        this.setSize(600, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("MENU");
        this.telaPrincipal.setBackground(Color.WHITE);
        iniciarElementos();
        configurarElementos();
        adicionarElementos();
        this.setVisible(true);
        File arquivo = new File(System.getProperty("user.dir") + "\\restoration\\backup_original.sql");
        if(arquivo.exists()){
            arquivo.delete();
        }
        File file = new File(System.getProperty("user.dir") + "\\restoration");
        if (file.exists()) {
            file.delete();
        }
    }

    public void iniciarElementos() {
        taskPaneContainer = new JXTaskPaneContainer();
        tpCadastrar = new JXTaskPane();
        tpRelatorio = new JXTaskPane();
        tpVisualizar = new JXTaskPane();
        tpPesquisar = new JXTaskPane();
        tpSistema = new JXTaskPane();
        tpCadastrar.add(new IrParaCadastroCliente());
        tpCadastrar.add(new IrParaCadastroProduto());
        tpCadastrar.add(new IrParaCadastroVenda());
        tpPesquisar.add(new IrParaPesquisarClienteProduto());
        tpPesquisar.add(new IrParaPesquisarVenda());
        tpRelatorio.add(new IrParaRelatorioCliente());
        tpRelatorio.add(new IrParaRelatorioProduto());
        tpRelatorio.add(new IrParaRelatorioVenda());
        tpVisualizar.add(new IrParaEstoque());
        tpVisualizar.add(new IrParaAvisos());
        tpSistema.add(new IrParaConfiguracoes());
        tpSistema.add(new IrParaCriarBackup());
        tpSistema.add(new IrParaImportarBackup());
        tpSistema.add(new IrParaSair());
        iconeCadastrar = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/cadastrar.png"));
        iconePesquisar = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/search.png"));
        iconeRelatorio = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/relatorio.png"));
        iconeVisualizar = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/visualizar.png"));
        iconeSistema = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/sistema.png"));
        ImageIcon image = new ImageIcon(this.getClass().getClassLoader().getResource("Imagens/logo.jpg"));
        logo = new JLabel(image);
        titulo = new JLabel("GERENCIADOR PARA REVENDEDORAS");

    }

    public void configurarElementos() {
        taskPaneContainer.setBounds(45, 100, 200, 400);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        titulo.setBounds(145, 25, 300, 50);
        logo.setBounds(300, 100, 200, 200);
        taskPaneContainer.setBackground(Color.white);
        tpCadastrar.setCollapsed(true);
        tpPesquisar.setCollapsed(true);
        tpVisualizar.setCollapsed(true);
        tpRelatorio.setCollapsed(true);
        tpSistema.setCollapsed(true);
        tpCadastrar.setTitle("CADASTRAR");
        tpPesquisar.setTitle("PESQUISAR");
        tpVisualizar.setTitle("VISUALIZAR");
        tpRelatorio.setTitle("RELATÓRIOS");
        tpSistema.setTitle("SISTEMA");
        tpCadastrar.setIcon(iconeCadastrar);
        tpPesquisar.setIcon(iconePesquisar);
        tpRelatorio.setIcon(iconeRelatorio);
        tpVisualizar.setIcon(iconeVisualizar);
        tpSistema.setIcon(iconeSistema);


    }

    public void adicionarElementos() {
        taskPaneContainer.add(tpCadastrar);
        taskPaneContainer.add(tpPesquisar);
        taskPaneContainer.add(tpRelatorio);
        taskPaneContainer.add(tpVisualizar);
        taskPaneContainer.add(tpSistema);
        this.add(titulo);
        this.add(logo);
        this.add(taskPaneContainer);
    }
}
