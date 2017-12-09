/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Exceptions.EmptyBankException;
import Exceptions.NotFoundItemException;
import JavaBeans.ConexaoBD;
import JavaBeans.Venda;
import java.awt.Color;
import java.awt.GradientPaint;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class Graficos extends javax.swing.JFrame {

    ConexaoBD conexao;
    ArrayList<Venda> lista;

    public Graficos() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        conexao = new ConexaoBD();
        lista = new <Venda> ArrayList();
        criaGrafico();
    }

    private CategoryDataset createDatasetMensal() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            lista = conexao.listarVendas(4, null, null);
            int size = lista.size();
            Calendar cal = GregorianCalendar.getInstance();
            int i = 0;
            while (size != 0) {
                if (lista.get(i).getCodigoVenda() == cal.get(Calendar.YEAR)) {
                    dataset.addValue(lista.get(i).getQuantidade(), lista.get(i).getNomeCliente(), lista.get(i).getNomeCliente());
                }
                size--;
                i++;

            }
        } catch (NotFoundItemException ex) {
            JOptionPane.showMessageDialog(null, "Não há vendas cadastradas no período mensal!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        }
        return dataset;
    }

    private CategoryDataset createDatasetAnual() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            lista = conexao.listarVendas(3, null, null);
            int size = lista.size();
            int i = 0;
            while (size != 0) {
                dataset.addValue(lista.get(i).getQuantidade(), lista.get(i).getNomeCliente(), lista.get(i).getNomeCliente());
                size--;
                i++;

            }
        } catch (NotFoundItemException ex) {
            JOptionPane.showMessageDialog(null, "Não há vendas cadastradas no período anual!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        }
        return dataset;
    }

    private CategoryDataset createDatasetDiario() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            lista = conexao.listarVendas(5, null, null);
            SimpleDateFormat formato = new SimpleDateFormat("dd");
            int size = lista.size();
            int i = 0;
            while (size != 0) {
                dataset.addValue(lista.get(i).getQuantidade(), lista.get(i).getData(), formato.format(lista.get(i).getData()));
                size--;
                i++;

            }
        } catch (NotFoundItemException ex) {
            JOptionPane.showMessageDialog(null, "Não há vendas cadastradas no período diário!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        } 
        return dataset;
    }

    public void criaGrafico() throws SQLException {
        //Cria gráfico diario
        CategoryDataset cds3 = createDatasetDiario();
        JFreeChart graf3 = ChartFactory.createBarChart3D("Gráfico Diário", "", "Quantidade", cds3, PlotOrientation.VERTICAL, false, true, true);
        ChartPanel myChartPanel3 = new ChartPanel(graf3, true);
        myChartPanel3.setSize(diario.getWidth(), diario.getHeight());
        myChartPanel3.setVisible(true);
        diario.removeAll();
        diario.add(myChartPanel3);
        diario.revalidate();
        diario.repaint();
        //Cria gráfico mensal
        CategoryDataset cds = createDatasetMensal();
        JFreeChart graf = ChartFactory.createBarChart3D("Gráfico Mensal", "", "Quantidade", cds, PlotOrientation.VERTICAL, false, true, true);
        ChartPanel myChartPanel = new ChartPanel(graf, true);
        myChartPanel.setSize(mensal.getWidth(), mensal.getHeight());
        myChartPanel.setVisible(true);
        mensal.removeAll();
        mensal.add(myChartPanel);
        mensal.revalidate();
        mensal.repaint();
        //Cria gráfico anual
        CategoryDataset cds2 = createDatasetAnual();
        JFreeChart graf2 = ChartFactory.createBarChart3D("Gráfico Anual", "", "Quantidade", cds2, PlotOrientation.VERTICAL, false, true, true);
        ChartPanel myChartPanel2 = new ChartPanel(graf2, true);
        myChartPanel2.setSize(anual.getWidth(), anual.getHeight());
        myChartPanel2.setVisible(true);
        anual.removeAll();
        anual.add(myChartPanel2);
        anual.revalidate();
        anual.repaint();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        voltar = new javax.swing.JButton();
        mensal = new javax.swing.JPanel();
        diario = new javax.swing.JPanel();
        anual = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gráficos");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mensalLayout = new javax.swing.GroupLayout(mensal);
        mensal.setLayout(mensalLayout);
        mensalLayout.setHorizontalGroup(
            mensalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        mensalLayout.setVerticalGroup(
            mensalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout diarioLayout = new javax.swing.GroupLayout(diario);
        diario.setLayout(diarioLayout);
        diarioLayout.setHorizontalGroup(
            diarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        diarioLayout.setVerticalGroup(
            diarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout anualLayout = new javax.swing.GroupLayout(anual);
        anual.setLayout(anualLayout);
        anualLayout.setHorizontalGroup(
            anualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        anualLayout.setVerticalGroup(
            anualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(anual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mensal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(voltar)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(diario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(mensal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(anual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(voltar))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        dispose();
    }//GEN-LAST:event_voltarActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel anual;
    private javax.swing.JPanel diario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mensal;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
