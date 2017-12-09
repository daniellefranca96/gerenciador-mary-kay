/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.util.ArrayList;

/**
 *
 * @author Danielle
 */
public class Venda {

    private double total;
    private int codigoCliente;
    private int codigoVenda;
    private String nomeCliente;
    private java.util.Date data;
    private int quantidade;
    private int parcelas;
    private ArrayList<Produto> produtosComprados;
    

    public Venda() {
        produtosComprados = new <Produto> ArrayList();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public java.util.Date getData() {
        return data;
    }

    public void setData(java.util.Date data) {
        this.data = data;
    }

      public ArrayList<Produto> getProdutosComprados() {
        return this.produtosComprados;
    }

    public void setProdutosComprados(ArrayList<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

   
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public int getParcelas() {
        return parcelas;
    }
    
    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

}
