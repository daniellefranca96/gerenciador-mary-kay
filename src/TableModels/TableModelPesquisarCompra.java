/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModels;

import JavaBeans.Venda;
import JavaBeans.Venda;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danielle
 */
public class TableModelPesquisarCompra extends AbstractTableModel {

    private ArrayList<Venda> linhas;
    private String[] colunas = new String[]{"DATA", "CLIENTE", "QUANTIDADE", "TOTAL","N° PARCELAS"};
    private static final int DATA = 0;
    private static final int CLIENTE = 1;
    private static final int QUANTIDADE = 2;
    private static final int TOTAL = 3;
    private static final int NUMPARCELAS = 4;

    public TableModelPesquisarCompra() {
        linhas = new ArrayList<Venda>();
    }

    public TableModelPesquisarCompra(ArrayList<Venda> listaDeCompras) {
        linhas = new ArrayList<Venda>(listaDeCompras);
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

    public Venda getCompra(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addCompra(Venda compra) {
        linhas.add(compra);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeComprs(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public ArrayList<Venda> getDadosTabela() {
        return linhas;
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda compra = linhas.get(rowIndex);
        switch (columnIndex) {
            case DATA:
                return compra.getData();
            case CLIENTE:
                return compra.getNomeCliente();
            case QUANTIDADE:
                return compra.getQuantidade();
            case TOTAL:
                DecimalFormat real = new DecimalFormat("R$ ,##0.00;(,##0.00)");
                String total = real.format(compra.getTotal());
                return total;
            case NUMPARCELAS:
                return compra.getParcelas();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
}
