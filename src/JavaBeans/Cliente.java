package JavaBeans;

import Exceptions.EmptyCampException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danielle S. França
 */
public class Cliente {

    private String nome, endereco, bairro, email, telefone, celular, cidade, cpf, observacoes;
    private java.util.Date dataNascimento;
    private int sexo, idade, codigo;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade() {
        if (getDataNascimento() != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String data = formato.format(dataNascimento);
            Date date = new Date(data);
            GregorianCalendar hj = new GregorianCalendar();
            GregorianCalendar nascimento = new GregorianCalendar();
            if (date != null) {
                nascimento.setTime(date);
            }
            int anohj = hj.get(Calendar.YEAR);
            int anoNascimento = nascimento.get(Calendar.YEAR);
            this.idade = new Integer(anohj - anoNascimento);
        } else {
            this.idade = 0;
        }
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public java.util.Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(java.util.Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws EmptyCampException {
        if (cpf.equals("   .   .   -  ")) {
            throw new EmptyCampException();
        } else {
            this.cpf = cpf;
        }
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
