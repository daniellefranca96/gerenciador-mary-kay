/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Exceptions.CodigoCadastrado;
import Exceptions.CpfCadastrado;
import Exceptions.EmailInvalido;
import Exceptions.EmptyBankException;
import Exceptions.NomeCadastrado;
import Exceptions.NotFoundItemException;
import Interfaces.CadastroCliente;
import JavaBeans.Cliente;
import JavaBeans.ConexaoBD;
import JavaBeans.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Verificacao {

    ConexaoBD conexao;
    ArrayList<Produto> listaProdutos;
    ArrayList<Cliente> listaClientes;

    public Verificacao() {
        conexao = new ConexaoBD();
        listaProdutos = new <Produto> ArrayList();
        listaClientes = new <Cliente> ArrayList();
    }

    public void verificarCodigo(int codigo) throws CodigoCadastrado {
        try {
            listaProdutos = conexao.listarProdutos();
            int cont = listaProdutos.size();
            int i = 0;
            while (cont != 0) {
                if (codigo == listaProdutos.get(i).getCodigo()) {
                    throw new CodigoCadastrado();
                }
                i++;
                cont--;
            }
        } catch (NotFoundItemException ex) {
        } catch (EmptyBankException ex) {
        }

    }

    public void verificarNome(String nome) throws NomeCadastrado {
        try {
            listaProdutos = conexao.listarProdutos();
            int cont = listaProdutos.size();
            int i = 0;
            while (cont != 0) {
                if (nome.equals(listaProdutos.get(i).getNome())) {
                    throw new NomeCadastrado();
                }
                i++;
                cont--;
            }
        } catch (NotFoundItemException ex) {
        } catch (EmptyBankException ex) {
        }
    }

    public void verificarCpf(String cpf) throws CpfCadastrado {
        try {
            listaClientes = conexao.listarClientes();
            int i = 0;
            int cont = listaClientes.size();
            while (cont != 0) {
                if (cpf.equals(listaClientes.get(i).getCpf())) {
                    throw new CpfCadastrado();
                }
                i++;
                cont--;
            }
        } catch (NotFoundItemException ex) {
        } catch (EmptyBankException ex) {
        }
    }

    public void verificarEmail(String email) throws EmailInvalido {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (!m.find()) {
            throw new EmailInvalido();
        }
    }
}
