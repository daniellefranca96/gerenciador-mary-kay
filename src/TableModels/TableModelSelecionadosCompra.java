/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import JavaBeans.Produto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danielle
 */
public class TableModelSelecionadosCompra extends AbstractTableModel {

    private ArrayList<Produto> linhas;
    private String[] colunas = new String[]{"CÓDIGO", "NOME", "ESTOQUE", "QTD"};
    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int ESTOQUE = 2;
    private static final int QUANTIDADE = 3;
    ArrayList<Produto> clone;

    public TableModelSelecionadosCompra(ArrayList<Produto> listaProdutos) {
        linhas = new ArrayList<Produto>(listaProdutos);
        clone = new ArrayList<Produto>();

    }

    public TableModelSelecionadosCompra() {
        linhas = new ArrayList<Produto>();
        clone = new ArrayList<Produto>();
    }

    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    ;
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return true;
        } else {
            return false;
        }

    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public Produto getProduto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProduto(Produto produto) {
        linhas.add(produto);
        Produto produto2 = new Produto();
        produto2.setQuantidade(0);
        clone.add(produto2);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public ArrayList<Produto> getDadosTabela() {
        return linhas;
    }

    public ArrayList<Produto> getQuantidades() {
        return clone;
    }

    public void clear() {
        linhas.clear();
        fireTableDataChanged();
    }

    public void setValueAt(Object obj, int rowIndex, int columnIndex) {
        try {
            Produto produto = linhas.get(rowIndex);
            Produto produto2 = clone.get(rowIndex);
            if (Integer.parseInt(obj.toString()) <= produto.getQuantidade()) {
                produto2.setQuantidade(Integer.parseInt(obj.toString()));
                fireTableCellUpdated(rowIndex, columnIndex);
            } else {
                int quantidade = produto2.getQuantidade();
                produto2.setQuantidade(quantidade);
                fireTableCellUpdated(rowIndex, columnIndex);
                JOptionPane.showMessageDialog(null, "Você não pode adicionar uma quantidade maior que a disponível no estoque!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = linhas.get(rowIndex);
        Produto produto2 = clone.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return produto.getCodigo();
            case NOME:
                return produto.getNome();
            case QUANTIDADE:
                return produto2.getQuantidade();
            case ESTOQUE:
                return produto.getQuantidade();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
}
