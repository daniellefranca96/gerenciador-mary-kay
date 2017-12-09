/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Actions.ButtonColumn;
import Actions.Imprimir;
import Exceptions.EmptyBankException;
import Exceptions.NotFoundItemException;
import JavaBeans.Aviso;
import JavaBeans.Cliente;
import JavaBeans.ConexaoBD;
import JavaBeans.Venda;
import Links.IrParaVisualizarCompra;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.hyperlink.AbstractHyperlinkAction;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.HyperlinkProvider;

/**
 *
 * @author Danielle S. França
 */
public class Avisos extends javax.swing.JFrame implements ActionListener {

    ArrayList<Cliente> listaCliente;
    ArrayList<Aviso> listaAviso;
    ConexaoBD conexao;
    String[] colunas1;
    String[] colunas2;
    int t = 0;

    public Avisos() {
        initComponents();
        this.setLocationRelativeTo(null);
        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane2.getViewport().setBackground(Color.white);
        listaCliente = new <Cliente> ArrayList();
        listaAviso = new <Aviso> ArrayList();
        selecao.setSelectedIndex(0);
        conexao = new ConexaoBD();
        conexao.getAvisos(3);
        colunas1 = new String[]{};
        colunas2 = new String[]{};
        aniversarios();
        avisos();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selecao.getSelectedIndex() == 2) {
                    listaCliente = conexao.getAniversarios(1);
                    if (!listaCliente.isEmpty()) {
                        DefaultTableModel modelo = new DefaultTableModel();
                        colunas1 = new String[]{"NOME", "IDADE", "CELULAR", "TELEFONE", "EMAIL"};
                        modelo.setColumnIdentifiers(new String[]{"NOME", "IDADE", "CELULAR", "TELEFONE", "EMAIL", "CLIENTE"});
                        for (int i = 0; i < listaCliente.size(); i++) {
                            modelo.addRow(new Object[]{listaCliente.get(i).getNome(), listaCliente.get(i).getIdade(), listaCliente.get(i).getCelular(), listaCliente.get(i).getTelefone(), listaCliente.get(i).getEmail(), "Visualize"});
                        }
                        tabela.setModel(modelo);
                        Action visualize = new AbstractAction() {
                            public void actionPerformed(ActionEvent e) {
                                JTable table = (JTable) e.getSource();
                                int row = table.convertRowIndexToModel(Integer.valueOf(e.getActionCommand()));
                                Cliente cliente = new Cliente();
                                cliente = conexao.getCliente(listaCliente.get(row).getCodigo());
                                EditarCliente editar = new EditarCliente(cliente, null);
                                editar.setVisible(true);
                            }
                        };
                        ButtonColumn buttonColumn = new ButtonColumn(tabela, visualize, 5);
                        buttonColumn.setMnemonic(KeyEvent.VK_D);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
                        tableModel.setNumRows(0);
                        tabela.setModel(tableModel);
                        JOptionPane.showMessageDialog(null, "Não há aniversário nesse período!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    aniversarios();
                }
            }
        };
        selecao.addActionListener(listener);
        ActionListener listener1 = new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                if (selecao1.getSelectedIndex() == 0) {
                    avisos();
                } else {
                    listaAviso.removeAll(listaAviso);
                    listaAviso = conexao.getAvisos(1);
                    if (!listaAviso.isEmpty()) {
                        DefaultTableModel model = new DefaultTableModel();
                        colunas2 = new String[]{"DATA COMPRA", "NOME CLIENTE", "NÍVEL REGRA"};
                        model.setColumnIdentifiers(new String[]{"DATA COMPRA", "NOME CLIENTE", "NÍVEL REGRA", "COMPRA", "CLIENTE"});
                        for (int i = 0; i < listaAviso.size(); i++) {
                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                            String dataCompra = formato.format(listaAviso.get(i).getDataCompra());
                            model.addRow(new Object[]{dataCompra, listaAviso.get(i).getNomeCliente(), listaAviso.get(i).getNivel(), "Visualize", "Visualize"});
                        }
                        avisos.setModel(model);
                        Action visualizeCliente = new AbstractAction() {
                            public void actionPerformed(ActionEvent e) {
                                JTable table = (JTable) e.getSource();
                                int row = table.convertRowIndexToModel(Integer.valueOf(e.getActionCommand()));
                                Cliente cliente = new Cliente();
                                cliente = conexao.getCliente(listaAviso.get(row).getCodigoCliente());
                                EditarCliente editar = new EditarCliente(cliente, null);
                                editar.setVisible(true);
                            }
                        };
                        Action visualizeCompra = new AbstractAction() {
                            public void actionPerformed(ActionEvent a) {
                                JTable table = (JTable) a.getSource();
                                int row = table.convertRowIndexToModel(Integer.valueOf(a.getActionCommand()));
                                ArrayList<Venda> venda = new <Venda> ArrayList();
                                try {
                                    venda = conexao.getVenda(1, listaAviso.get(row).getCodigoCompra());
                                } catch (ParseException ex) {
                                    Logger.getLogger(Avisos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                MostrarCompra mostrarCompra = new MostrarCompra(venda.get(0));
                                mostrarCompra.setVisible(true);
                            }
                        };
                        ButtonColumn compra = new ButtonColumn(avisos, visualizeCompra, 4);
                        compra.setMnemonic(KeyEvent.VK_D);
                        ButtonColumn cliente = new ButtonColumn(avisos, visualizeCliente, 5);
                        cliente.setMnemonic(KeyEvent.VK_D);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) avisos.getModel();
                        tableModel.setNumRows(0);
                        avisos.setModel(tableModel);
                        JOptionPane.showMessageDialog(null, "Não há avisos nesse período!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };
        selecao1.addActionListener(listener1);
    }

    public void aniversarios() {
        if (selecao.getSelectedIndex() == 1) {
            listaCliente = conexao.getAniversarios(2);
        }
        if (selecao.getSelectedIndex() == 0) {
            listaCliente = conexao.getAniversarios(3);
        }
        if (!listaCliente.isEmpty()) {
            DefaultTableModel modelo = new DefaultTableModel();
            colunas1 = new String[]{"DATA", "NOME", "IDADE", "CELULAR", "TELEFONE", "EMAIL"};
            modelo.setColumnIdentifiers(new String[]{"DATA", "NOME", "IDADE", "CELULAR", "TELEFONE", "EMAIL", "CLIENTE"});
            for (int i = 0; i < listaCliente.size(); i++) {
                modelo.addRow(new Object[]{listaCliente.get(i).getBairro(), listaCliente.get(i).getNome(), listaCliente.get(i).getIdade(), listaCliente.get(i).getCelular(), listaCliente.get(i).getTelefone(), listaCliente.get(i).getEmail(), "Visualize"});
            }
            tabela.setModel(modelo);
            Action visualize = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int row = table.convertRowIndexToModel(Integer.valueOf(e.getActionCommand()));
                    int cod = listaCliente.get(row).getCodigo();
                    Cliente cliente = new Cliente();
                    cliente = conexao.getCliente(cod);
                    EditarCliente editar = new EditarCliente(cliente, null);
                    editar.setVisible(true);
                }
            };
            ButtonColumn buttonColumn = new ButtonColumn(tabela, visualize, 6);
            buttonColumn.setMnemonic(KeyEvent.VK_D);
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
            tableModel.setNumRows(0);
            tabela.setModel(tableModel);
            JOptionPane.showMessageDialog(null, "Não há aniversário nesse período!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void avisos() {
        listaAviso.removeAll(listaAviso);
        listaAviso = conexao.getAvisos(2);
        if (!listaAviso.isEmpty()) {
            DefaultTableModel model = new DefaultTableModel();
            colunas2 = new String[]{"DATA AVISO", "DATA COMPRA", "NOME CLIENTE", "NÍVEL REGRA"};
            model.setColumnIdentifiers(new String[]{"DATA AVISO", "DATA COMPRA", "NOME CLIENTE", "NÍVEL REGRA", "COMPRA", "CLIENTE"});
            for (int i = 0; i < listaAviso.size(); i++) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String dataCompra = formato.format(listaAviso.get(i).getDataCompra());
                String dataAviso = formato.format(listaAviso.get(i).getDataAviso());
                model.addRow(new Object[]{dataAviso, dataCompra, listaAviso.get(i).getNomeCliente(), listaAviso.get(i).getNivel(), "Visualize", "Visualize"});
            }
            avisos.setModel(model);
            Action visualizeCliente = new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    JTable table = (JTable) e.getSource();
                    int row = table.convertRowIndexToModel(Integer.valueOf(e.getActionCommand()));
                    Cliente cliente = new Cliente();
                    cliente = conexao.getCliente(listaAviso.get(row).getCodigoCliente());
                    EditarCliente editar = new EditarCliente(cliente, null);
                    editar.setVisible(true);
                }
            };
            Action visualizeCompra = new AbstractAction() {
                public void actionPerformed(ActionEvent a) {
                    JTable table = (JTable) a.getSource();
                    int row = table.convertRowIndexToModel(Integer.valueOf(a.getActionCommand()));
                    ArrayList<Venda> venda = new <Venda> ArrayList();
                    try {
                        venda = conexao.getVenda(1, listaAviso.get(row).getCodigoCompra());
                    } catch (ParseException ex) {
                        Logger.getLogger(Avisos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MostrarCompra mostrarCompra = new MostrarCompra(venda.get(0));
                    mostrarCompra.setVisible(true);
                }
            };
            ButtonColumn compra = new ButtonColumn(avisos, visualizeCompra, 4);
            compra.setMnemonic(KeyEvent.VK_D);
            ButtonColumn cliente = new ButtonColumn(avisos, visualizeCliente, 5);
            cliente.setMnemonic(KeyEvent.VK_D);
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) avisos.getModel();
            tableModel.setNumRows(0);
            avisos.setModel(tableModel);
            JOptionPane.showMessageDialog(null, "Não há avisos nesse período!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        periodo = new javax.swing.JLabel();
        selecao = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        imprimir = new javax.swing.JButton();
        periodo1 = new javax.swing.JLabel();
        selecao1 = new javax.swing.JComboBox();
        imprimirAvisos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        salvarPdf = new javax.swing.JButton();
        salvarPdf1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        avisos = new javax.swing.JTable();
        voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Avisos");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Avisos"));

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Data", "Nome ", "Idade", "Celular", "Telefone", "E-Mail", "Cliente"
            }
        ));
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setGridColor(new java.awt.Color(255, 255, 255));
        tabela.setRowSorter(null);
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getColumn(2).setHeaderValue("Idade");
        tabela.getColumnModel().getColumn(3).setHeaderValue("Celular");
        tabela.getColumnModel().getColumn(4).setHeaderValue("Telefone");
        tabela.getColumnModel().getColumn(5).setHeaderValue("E-Mail");

        periodo.setText("Período:");

        selecao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Anual", "Mensal", "Diário" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ANIVERSÁRIOS");

        imprimir.setText("Imprimir Aniversários");
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });

        periodo1.setText("Período:");

        selecao1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mensal", "Diário" }));

        imprimirAvisos.setText("Imprimir Avisos");
        imprimirAvisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirAvisosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("AVISOS");

        salvarPdf.setText("Salvar PDF");
        salvarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarPdfActionPerformed(evt);
            }
        });

        salvarPdf1.setText("Salvar PDF");
        salvarPdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarPdf1ActionPerformed(evt);
            }
        });

        avisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DATA AVISO", "DATA COMPRA", "NOME CLIENTE", "NÍVEL REGRA", "COMPRA", "CLIENTE"
            }
        ));
        avisos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        avisos.setCellSelectionEnabled(true);
        avisos.setGridColor(new java.awt.Color(255, 255, 255));
        avisos.setRowSorter(null);
        jScrollPane2.setViewportView(avisos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 24, Short.MAX_VALUE)
                        .addComponent(periodo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selecao1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(imprimirAvisos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(salvarPdf1)
                        .addGap(170, 170, 170))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(periodo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(selecao, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(imprimir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(salvarPdf)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(periodo)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(selecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimir)
                        .addComponent(salvarPdf)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(periodo1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(selecao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(imprimirAvisos)
                        .addComponent(salvarPdf1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(326, 326, 326))
        );

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(voltar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(voltar)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
        Imprimir imprimir = new Imprimir(tabela);
    }//GEN-LAST:event_imprimirActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void imprimirAvisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirAvisosActionPerformed
        Imprimir imprimir = new Imprimir(avisos);
    }//GEN-LAST:event_imprimirAvisosActionPerformed

    private void salvarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarPdfActionPerformed
        Selecionar selecionar = null;
        try {
            tabela.removeColumn(tabela.getColumn("CLIENTE"));
            selecionar = new Selecionar(tabela, 1, "Aniversários", colunas1);
        } catch (Exception ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        selecionar.setVisible(false);
        selecionar.fechar();
    }//GEN-LAST:event_salvarPdfActionPerformed

    private void salvarPdf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarPdf1ActionPerformed
        Selecionar selecionar = null;
        try {
            avisos.removeColumn(avisos.getColumn("COMPRA"));
            avisos.removeColumn(avisos.getColumn("CLIENTE"));
            selecionar = new Selecionar(avisos, 1, "Avisos", colunas2);
        } catch (Exception ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        selecionar.fechar();
    }//GEN-LAST:event_salvarPdf1ActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable avisos;
    private javax.swing.JButton imprimir;
    private javax.swing.JButton imprimirAvisos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel periodo;
    private javax.swing.JLabel periodo1;
    private javax.swing.JButton salvarPdf;
    private javax.swing.JButton salvarPdf1;
    private javax.swing.JComboBox selecao;
    private javax.swing.JComboBox selecao1;
    private javax.swing.JTable tabela;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
