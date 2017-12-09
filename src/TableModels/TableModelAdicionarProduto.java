/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import JavaBeans.Produto;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danielle
 */
public class TableModelAdicionarProduto extends AbstractTableModel {

    private ArrayList<Produto> linhas;
    private String[] colunas = new String[]{"CÓDIGO", "NOME"};
    private static final int CODIGO = 0;
    private static final int NOME = 1;

    public TableModelAdicionarProduto() {
        linhas = new ArrayList<Produto>();
    }

    public TableModelAdicionarProduto(ArrayList<Produto> listaDeProdutos) {
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
        return false;

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

    public void removeAll(ArrayList<Produto> produtos) {
        int i = 0;
        int size = produtos.size();
        while (size != 0) {
            linhas.remove(produtos.get(i));
            i++;
            size--;
        }
    }

    public ArrayList<Produto> getDados() {
        return linhas;
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = linhas.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return produto.getCodigo();
            case NOME:
                return produto.getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }
}
