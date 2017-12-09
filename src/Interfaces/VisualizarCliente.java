/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import JavaBeans.Cliente;
import Actions.Imprimir;
import Exceptions.EmptyBankException;
import Exceptions.NotFoundItemException;
import JavaBeans.ConexaoBD;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Danielle S. França
 */
public class VisualizarCliente extends javax.swing.JFrame {

    String sexo = "";
    ArrayList<Cliente> lista;
    JMenuItem imprimir;

    public VisualizarCliente() {
        initComponents();
        setLocationRelativeTo(null);
        jScrollPane1.getViewport().setBackground(Color.white);
        lista = new <Cliente> ArrayList();
        listarTodos();
    }

    @SuppressWarnings("unchecked")
    public void listarTodos() {
        try {
            ConexaoBD conexao = new ConexaoBD();
            lista = conexao.listarClientes();
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.setColumnIdentifiers(new String[]{"NOME", "CPF", "SEXO", "DATA DE NASC.", "IDADE", "ENDEREÇO", "TELEFONE", "CELULAR", "E-MAIL"});
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getSexo() == 1) {
                        sexo = "Masculino";
                    } else {
                        sexo = "Feminino";
                    }
                     String data=null;
                    if (this.lista.get(i).getDataNascimento() != null) {
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        data = formato.format(this.lista.get(i).getDataNascimento());
                    }
                    modelo.addRow(new Object[]{this.lista.get(i).getNome(), this.lista.get(i).getCpf(), sexo, data, this.lista.get(i).getIdade(), this.lista.get(i).getCidade() + ", " + this.lista.get(i).getBairro() + ", " + this.lista.get(i).getEndereco(), this.lista.get(i).getTelefone(), this.lista.get(i).getCelular(), this.lista.get(i).getEmail()});
                }
                tabela.setModel(modelo);
                tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
                tabela.getColumnModel().getColumn(5).setPreferredWidth(200);
                tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
                tabela.getColumnModel().getColumn(8).setPreferredWidth(150);
        } catch (NotFoundItemException ex) {
            JOptionPane.showMessageDialog(null, "Esse nome não está cadastrado no sistema!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        } catch (EmptyBankException ex) {
            JOptionPane.showMessageDialog(null, "Não há dados cadastrados no sistema!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        voltar = new javax.swing.JButton();
        salvarPdf = new javax.swing.JButton();
        imprimirTabela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório Clientes");
        setResizable(false);

        painel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualizar Clientes"));

        tabela.setAutoCreateRowSorter(true);
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NOME", "CPF", "SEXO", "DATA DE NASC.", "IDADE", "ENDEREÇO", "TELEFONE", "CELULAR", "E-MAIL"
            }
        ));
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setGridColor(new java.awt.Color(255, 255, 255));
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        salvarPdf.setText("Salvar PDF");
        salvarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarPdfActionPerformed(evt);
            }
        });

        imprimirTabela.setText("Imprimir");
        imprimirTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirTabelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel1Layout = new javax.swing.GroupLayout(painel1);
        painel1.setLayout(painel1Layout);
        painel1Layout.setHorizontalGroup(
            painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel1Layout.createSequentialGroup()
                .addGroup(painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel1Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(imprimirTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salvarPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(voltar))
                    .addGroup(painel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        painel1Layout.setVerticalGroup(
            painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(imprimirTabela)
                    .addComponent(salvarPdf)
                    .addComponent(voltar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void salvarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarPdfActionPerformed
        Selecionar selecionar = null;
        try {
            String[] colunas = new String[]{"NOME", "CPF", "SEXO", "DATA DE NASC.", "IDADE", "ENDEREÇO", "TELEFONE", "CELULAR", "E-MAIL"};
            selecionar = new Selecionar(tabela, 1, "Clientes", colunas);
        } catch (Exception ex) {
            Logger.getLogger(Estoque.class.getName()).log(Level.SEVERE, null, ex);
        }
        selecionar.fechar();
    }//GEN-LAST:event_salvarPdfActionPerformed

    private void imprimirTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirTabelaActionPerformed
        Imprimir imprimir = new Imprimir(tabela);
    }//GEN-LAST:event_imprimirTabelaActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton imprimirTabela;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel painel1;
    private javax.swing.JButton salvarPdf;
    private javax.swing.JTable tabela;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
