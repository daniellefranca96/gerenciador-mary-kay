/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import JavaBeans.Cliente;
import JavaBeans.Venda;
import JavaBeans.ConexaoBD;
import JavaBeans.Produto;
import TableModels.TableModelEstoque;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Danielle S. França
 */
public class Comprar extends javax.swing.JFrame implements TableModelListener {

    ArrayList<Produto> lista;
    Cliente cliente;
    JFrame parent;
    DecimalFormat real;
    ConexaoBD conexao;

    public Comprar() {
        initComponents();
        setLocationRelativeTo(null);
        lista = new <Produto> ArrayList();
        jScrollPane1.getViewport().setBackground(Color.white);
        TableModelEstoque tableModel = new TableModelEstoque(1);
        tabela.setModel(tableModel);
        cliente = new Cliente();
        real = new DecimalFormat("R$ ,##0.00;(,##0.00)");
        conexao = new ConexaoBD();

    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        tcliente.setText(cliente.getNome());
        TableModelEstoque model = (TableModelEstoque) tabela.getModel();
        /*
        int i = 0;
        int cont = lista.size();
        while (cont != 0) {
            model.addProduto(lista.get(i));
            i++;
            cont--;
        }
        tabela.setModel(model);
        */ 

    }

    public void setFrame(JFrame parent) {
        this.parent = parent;
    }

    public void setTotal() {
        TableModelEstoque model = (TableModelEstoque) tabela.getModel();
        model.removeProduto(tabela.getSelectedRow());
        ttotal.setText(real.format(model.getTotal()));
    }

    public void setProdutos(ArrayList<Produto> lista) {
        lista.removeAll(this.lista);
        this.lista = lista;
        int i = 0;
        int cont = lista.size();
        TableModelEstoque model = (TableModelEstoque) tabela.getModel();
        while (cont != 0) {
            model.addProduto(lista.get(i));
            i++;
            cont--;
        }
        ttotal.setText(Double.toString(model.getTotal()));
    }

    public void tableChanged(TableModelEvent e) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nomeCliente = new javax.swing.JLabel();
        tcliente = new javax.swing.JLabel();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        remover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ttotal = new javax.swing.JLabel();
        adicionar = new javax.swing.JButton();
        parcelas = new javax.swing.JLabel();
        tparcelas = new javax.swing.JComboBox();
        comprar = new javax.swing.JButton();
        voltar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar Venda");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        nomeCliente.setText("Cliente:");

        tcliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        buscar.setText("Buscar Cliente");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Título 4", "Title 4"
            }
        ));
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        remover.setText("Remover Produto");
        remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("Total da Compra: ");

        ttotal.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        adicionar.setText("Adicionar Produtos");
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        parcelas.setText("N° de Parcelas:");

        tparcelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomeCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(adicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(remover)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(buscar)
                                .addGap(125, 125, 125)
                                .addComponent(parcelas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tparcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(61, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomeCliente)
                        .addComponent(buscar)
                        .addComponent(parcelas)
                        .addComponent(tparcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ttotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(remover)
                                .addComponent(adicionar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        comprar.setText("Cadastrar Venda");
        comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprarActionPerformed(evt);
            }
        });

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(comprar)
                        .addGap(18, 18, 18)
                        .addComponent(voltar)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(comprar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        BuscarCliente buscarCliente = new BuscarCliente(this);
        buscarCliente.setVisible(true);
    }//GEN-LAST:event_buscarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerActionPerformed
        int linha = tabela.getSelectedRow();
        if (linha != -1) {
            TableModelEstoque model = (TableModelEstoque) tabela.getModel();
            lista.remove(model.getProduto(tabela.getSelectedRow()));
            model.removeProduto(tabela.getSelectedRow());
            tabela.setModel(model);
            ttotal.setText(Double.toString(model.getTotal()));

        }

    }//GEN-LAST:event_removerActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        if (parent == null) {
            AdicionarProduto adicionar = new AdicionarProduto(2, this);
            adicionar.setVisible(true);
        } else {
            AdicionarProduto adicionar = new AdicionarProduto(2, this, lista);
            adicionar.setVisible(true);
        }
    }//GEN-LAST:event_adicionarActionPerformed

    private void tabelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyReleased
        TableModelEstoque model = (TableModelEstoque) tabela.getModel();
        ttotal.setText(Double.toString(model.getTotal()));
    }//GEN-LAST:event_tabelaKeyReleased

    private void comprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprarActionPerformed
        TableModelEstoque model = (TableModelEstoque) tabela.getModel();
        if (!model.getDadosTabela().isEmpty()) {
            if (!tcliente.getText().equals("")) {
                Venda compra = new Venda();
                Date data = new Date(System.currentTimeMillis());
                compra.setData(data);
                compra.setCodigoCliente(cliente.getCodigo());
                compra.setTotal(model.getTotal());
                compra.setParcelas(Integer.parseInt(tparcelas.getSelectedItem().toString()));
                compra.setProdutosComprados(model.getDadosTabela());
                conexao.setVenda(compra);
                parent = null;
                ArrayList l = new <Produto>ArrayList();
                TableModelEstoque limpar = new TableModelEstoque(l);
                tabela.setModel(limpar);
                tcliente.setText("");
                ttotal.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Você precisa selecionar o cliente!", "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você precisa adicionar produtos a compra!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_comprarActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JButton buscar;
    private javax.swing.JButton comprar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomeCliente;
    private javax.swing.JLabel parcelas;
    private javax.swing.JButton remover;
    private javax.swing.JTable tabela;
    private javax.swing.JLabel tcliente;
    private javax.swing.JComboBox tparcelas;
    private javax.swing.JLabel ttotal;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
