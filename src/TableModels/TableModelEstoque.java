package TableModels;

import JavaBeans.Produto;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danielle S. França
 */
public class TableModelEstoque extends AbstractTableModel {

    private ArrayList<Produto> linhas;
    private String[] colunas = new String[]{"CÓDIGO", "NOME", "QUANTIDADE", "VALOR", "TOTAL"};
    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int QUANTIDADE = 2;
    private static final int VALOR = 3;
    private static final int TOTAL = 4;
    ArrayList<Integer> editadas;
    private int indice = 0;
    DecimalFormat real;

    public TableModelEstoque(int indice) {
        this.indice = indice;
        linhas = new ArrayList<Produto>();
        editadas = new <Integer> ArrayList();
        real = new DecimalFormat("R$ ,##0.00;(,##0.00)");

    }

    public TableModelEstoque(ArrayList<Produto> listaDeProdutos) {
        linhas = new ArrayList<Produto>(listaDeProdutos);
        editadas = new <Integer> ArrayList();
        real = new DecimalFormat("R$ ,##0.00;(,##0.00)");
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
        if (indice == 1) {
            return false;
        } else {
            if (columnIndex == 2 || columnIndex == 3) {
                return true;
            } else {
                return false;
            }
        }

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

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public double getTotal() {
        int i = 0;
        int cont = linhas.size();
        double total = 0;
        while (cont != 0) {
            total = total + linhas.get(i).getTotal();
            i++;
            cont--;
        }
        return total;
    }

    public ArrayList getLinhasEditadas() {
        return editadas;
    }

    public void setValueAt(Object obj, int rowIndex, int columnIndex) {
        try {
            Produto produto = linhas.get(rowIndex);
            switch (columnIndex) {
                case VALOR:
                    String valor = obj.toString();
                    valor = valor.replace(".", "");
                    valor = valor.replace("R$", "").trim();
                    valor = valor.replace(",", ".");
                    produto.setValor(Double.parseDouble(valor));
                    produto.setTotal();
                case QUANTIDADE:
                    produto.setQuantidade(Integer.parseInt(obj.toString()));
                    produto.setTotal();
            }
            fireTableCellUpdated(rowIndex, 3);
            fireTableCellUpdated(rowIndex, columnIndex);
            editadas.add(rowIndex);
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
            case VALOR:
                String valor = real.format(produto.getValor());
                return valor;
            case TOTAL:
                String total = real.format(produto.getTotal());
                return total;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }
}
