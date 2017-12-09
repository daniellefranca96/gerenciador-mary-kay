/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.util.Date;

/**
 *
 * @author Danielle
 */
public class Aviso {
    
private Date dataAviso;
private Date dataCompra;
private String NomeCliente;
private int codigoCliente;
private int codigoCompra;
private String nivel;

    /**
     * @return the dataAviso
     */
    public Date getDataAviso() {
        return dataAviso;
    }

    /**
     * @param dataAviso the dataAviso to set
     */
    public void setDataAviso(Date dataAviso) {
        this.dataAviso = dataAviso;
    }

    /**
     * @return the dataCompra
     */
    public Date getDataCompra() {
        return dataCompra;
    }

    /**
     * @param dataCompra the dataCompra to set
     */
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     * @return the NomeCliente
     */
    public String getNomeCliente() {
        return NomeCliente;
    }

    /**
     * @param NomeCliente the NomeCliente to set
     */
    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    /**
     * @return the codigoCliente
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente the codigoCliente to set
     */
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return the codigoCompra
     */
    public int getCodigoCompra() {
        return codigoCompra;
    }

    /**
     * @param codigoCompra the codigoCompra to set
     */
    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
}
