/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import JavaBeans.Produto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danielle
 */
public class TableModelSelecionados extends AbstractTableModel {

    private ArrayList<Produto> linhas;
    private String[] colunas = new String[]{"CÓDIGO", "NOME", "QTD"};
    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int QUANTIDADE = 2;

    public TableModelSelecionados() {
        linhas = new ArrayList<Produto>();
    }

    public TableModelSelecionados(ArrayList<Produto> listaDeProdutos) {
        linhas = new ArrayList<Produto>(listaDeProdutos);
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
        if (columnIndex == 2) {
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

    public void clear() {
        linhas.clear();
        fireTableDataChanged();
    }

    public void setValueAt(Object obj, int rowIndex, int columnIndex) {
        try {
            Produto produto = linhas.get(rowIndex);
            produto.setQuantidade(Integer.parseInt(obj.toString()));
            produto.setTotal();
            fireTableCellUpdated(rowIndex, columnIndex);
        } catch (NumberFormatException ex) {
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = linhas.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return produto.getCodigo();
            case NOME:
                return produto.getNome();
            case QUANTIDADE:
                return produto.getQuantidade();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
}