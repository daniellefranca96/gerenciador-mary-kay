/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import Exceptions.EmptyCampException;

/**
 *
 * @author Danielle S. França
 */
public class Produto {

    private int codigo, codp;
    private String nome;
    private int quantidade;
    private double valor;
    private String genero;
    private double total;
    private String imagem;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws EmptyCampException {
        if (codigo == -1) {
            throw new EmptyCampException();
        } else {
            this.codigo = codigo;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws EmptyCampException {
        if (nome.equals("")) {
            throw new EmptyCampException();
        } else {
            this.nome = nome;
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) throws EmptyCampException {
        if (valor == -1) {
            throw new EmptyCampException();
        } else {
            this.valor = valor;
        }

    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        total = quantidade * valor;
    }

    public int getCodp() {
        return codp;
    }

    public void setCodp(int codp) {
        this.codp = codp;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getImagem() {
        return imagem;
    }
    
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
