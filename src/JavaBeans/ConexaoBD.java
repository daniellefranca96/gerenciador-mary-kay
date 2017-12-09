/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import Actions.Descompactar;
import Actions.Recuperacao;
import Exceptions.EmptyBankException;
import Exceptions.NotFoundItemException;
import Interfaces.CadastroCliente;
import Interfaces.EditarCliente;
import Interfaces.Selecionar;
import Interfaces.TelaLogin;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Danielle
 */
public class ConexaoBD {

    private static String url = "jdbc:mysql://127.0.0.1:3307/projetofinal";
    private static String user = "root";
    private static String senha = "W48d3o14";
    private Usuario usuario;
    private Produto produto;
    private Cliente cliente;
    private Venda venda;
    ArrayList<Cliente> lista;
    ArrayList<Produto> listaProduto;
    ArrayList<Venda> listaVenda;
    ArrayList<Aviso> listaAviso;
    boolean testeBanco;

    public Usuario getUsuario() throws IOException, SQLException, ClassNotFoundException, URISyntaxException, Exception {
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT* FROM usuario;";
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            usuario = new Usuario();
            while (rs.next()) {
                usuario.setUser(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Não é possível estabelecer a conex com o banco de dados!");
//            Recuperacao recuperar = new Recuperacao();
//            recuperar.recuperar();
//            String caminho = null;
//            String origem = null;
//            UIManager.put("OptionPane.yesButtonText", "Importar");
//            UIManager.put("OptionPane.noButtonText", "Restaurar");
//            int res = JOptionPane.showConfirmDialog(null, "Devido a exclusão do banco de dados não é possível entrar no sistema, você deve escolher entre as duas opções abaixo,\nonde IMPORTAR permite a importação de um bakcup do banco de dados criado previamente e RESTAURAR restaura o \nsistema para a configuração original onde todos os seus dados deixaram de existir.", "AVISO!", JOptionPane.YES_NO_CANCEL_OPTION);
//            if (res == JOptionPane.NO_OPTION) {
//                Descompactar descompacta = new Descompactar();
//                String destino = System.getProperty("user.dir");
//                File file = new File(System.getProperty("user.dir") + "\\dist\\ProjetoFinal.jar");
//                if (file.exists()) {
//                    origem = System.getProperty("user.dir") + "\\dist\\ProjetoFinal.jar";
//                } else {
//                    origem = System.getProperty("user.dir") + "\\ProjetoFinal.jar";
//                }
//                descompacta.descompacta(origem, destino);
//                caminho = destino + "\\restoration\\backup_original.sql";
//                ConexaoBD conexao = new ConexaoBD();
//                boolean confirma = conexao.importarBackupSql(caminho);
//                JOptionPane.showMessageDialog(null, "As configurações do banco de dados foram restauradas com sucesso\nRefaça o login para entrar no sistema!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
//            } else if (res == JOptionPane.YES_OPTION) {
//                Selecionar selecionar = new Selecionar(null, 2, "importar2", null);
//                selecionar.fechar();  
//            }
        }
        return null;
    }

    public void setUsuario(Usuario usuario) {
        try {
            this.usuario = usuario;
            Connection conexao = DriverManager.getConnection(url, user, senha);
            int cod = 1;
            String update = "UPDATE Usuario SET usuario='" + usuario.getUser() + "',senha ='" + usuario.getSenha() + "',email='" + usuario.getEmail() + "'WHERE coduser='" + cod + "';";
            PreparedStatement stmt = conexao.prepareStatement(update);
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public int setCliente(int op, Cliente cliente) {
        int i = 0;
        try {
            this.cliente = cliente;
            if (op == 1) {
                Connection conexao = DriverManager.getConnection(url, user, senha);
                String sql = "INSERT INTO Cliente (NOME, ENDERECO, BAIRRO, CIDADE,OBSERVACOES, CPF, SEXO, TELEFONE, CELULAR, DATANASCIMENTO, EMAIL) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEndereco());
                stmt.setString(3, cliente.getBairro());
                stmt.setString(4, cliente.getCidade());
                stmt.setString(5, cliente.getObservacoes());
                stmt.setString(6, cliente.getCpf());
                stmt.setInt(7, cliente.getSexo());
                stmt.setString(8, cliente.getTelefone());
                stmt.setString(9, cliente.getCelular());
                java.util.Date dt = cliente.getDataNascimento();
                if (cliente.getDataNascimento() == null) {
                    stmt.setDate(10, null);
                } else {
                    stmt.setDate(10, new java.sql.Date(dt.getTime()));
                }
                stmt.setString(11, cliente.getEmail());
                JOptionPane.showMessageDialog(null, "Seus dados foram cadastrados!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                stmt.executeUpdate();
                stmt.close();
                conexao.close();
            }
            if (op == 2) {
                String update;
                if (cliente.getDataNascimento() == null) {
                    update = "UPDATE Cliente SET nome='" + cliente.getNome() + "',endereco ='" + cliente.getEndereco() + "',bairro ='" + cliente.getBairro() + "',cidade ='" + cliente.getCidade() + "',observacoes='" + cliente.getObservacoes() + "',cpf ='" + cliente.getCpf() + "',telefone ='" + cliente.getTelefone() + "',celular ='" + cliente.getCelular() + "',dataNascimento = null,email ='" + cliente.getEmail() + "'WHERE codigo='" + cliente.getCodigo() + "';";
                } else {
                    update = "UPDATE Cliente SET nome='" + cliente.getNome() + "',endereco ='" + cliente.getEndereco() + "',bairro ='" + cliente.getBairro() + "',cidade ='" + cliente.getCidade() + "',observacoes='" + cliente.getObservacoes() + "',cpf ='" + cliente.getCpf() + "',telefone ='" + cliente.getTelefone() + "',celular ='" + cliente.getCelular() + "',dataNascimento ='" + new java.sql.Date(cliente.getDataNascimento().getTime()) + "',email ='" + cliente.getEmail() + "'WHERE codigo='" + cliente.getCodigo() + "';";
                }
                Connection con = DriverManager.getConnection(url, user, senha);
                PreparedStatement stmt = con.prepareStatement(update);
                stmt.executeUpdate();
                stmt.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
            }
            if (op == 3) {
                Connection con = DriverManager.getConnection(url, user, senha);
                String sql = "SELECT*  FROM compra WHERE cod_c=" + cliente.getCodigo() + ";";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                if (rs.first() == false) {
                    sql = "DELETE FROM Cliente WHERE codigo ='" + cliente.getCodigo() + "';";
                    stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                    stmt.close();
                    con.close();
                    lista.remove(cliente);
                    i = 1;
                    JOptionPane.showMessageDialog(null, "Item excluído!", "SUCESSO!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Não permitido excluir pois o cliente está cadastrado em uma venda!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
                rs.close();
                stmt.close();
                con.close();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return i;
    }

    public ArrayList<Cliente> getClientes(String nome) throws NotFoundItemException {
        try {
            lista.removeAll(lista);
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT * FROM Cliente WHERE nome like '%" + nome + "%' order by nome;";
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setObservacoes(rs.getString("observacoes"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSexo(rs.getInt("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setDataNascimento(rs.getDate("dataNascimento"));
                cliente.setIdade();
                cliente.setEmail(rs.getString("email"));
                lista.add(cliente);
            }
            rs.close();
            stmt.close();
            conexao.close();
            if (lista.isEmpty()) {
                throw new NotFoundItemException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lista;
    }

    public Cliente getCliente(int codigo) throws NotFoundItemException {
        try {
            testeBanco = false;
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT * FROM Cliente WHERE codigo=" + codigo;
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                testeBanco = true;
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setObservacoes(rs.getString("observacoes"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSexo(rs.getInt("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setDataNascimento(rs.getDate("dataNascimento"));
                cliente.setIdade();
                cliente.setEmail(rs.getString("email"));
            }
            rs.close();
            stmt.close();
            conexao.close();
            if (!testeBanco) {
                throw new NotFoundItemException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return cliente;
    }

    public ArrayList<Cliente> listarClientes() throws EmptyBankException {
        try {
            testeBanco = false;
            lista.removeAll(lista);
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT* FROM Cliente ORDER BY nome";
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                testeBanco = true;
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setObservacoes(rs.getString("observacoes"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSexo(rs.getInt("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setDataNascimento(rs.getDate("dataNascimento"));
                cliente.setIdade();
                cliente.setEmail(rs.getString("email"));
                lista.add(cliente);
            }
            rs.close();
            stmt.close();
            conexao.close();
            if (!testeBanco) {
                throw new EmptyBankException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lista;
    }

    public int setProduto(int op, Produto produto) {
        int i = 0;
        try {
            if (op == 1) {
                this.produto = produto;
                Connection conexao = DriverManager.getConnection(url, user, senha);
                String sql = "INSERT INTO Produto (codigo,nome,quantidade,valor,imagem) VALUES (?,?,?,?,?);";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, produto.getCodigo());
                stmt.setString(2, produto.getNome());
                stmt.setInt(3, produto.getQuantidade());
                stmt.setDouble(4, produto.getValor());
                stmt.setString(5, produto.getImagem());
                stmt.executeUpdate();
                stmt.close();
                conexao.close();
            }
            if (op == 2) {
                Connection con = DriverManager.getConnection(url, user, senha);
                String update = "UPDATE Produto SET codigo='" + produto.getCodigo() + "',nome ='" + produto.getNome() + "',quantidade ='" + produto.getQuantidade() + "',valor ='" + produto.getValor() + "',imagem='" + produto.getImagem() + "' WHERE codp='" + produto.getCodp() + "';";
                PreparedStatement stmt = con.prepareStatement(update);
                stmt.executeUpdate();
                stmt.close();
                con.close();
            }
            if (op == 3) {
                Connection con = DriverManager.getConnection(url, user, senha);
                String sql = "SELECT*  FROM produtoscomprados WHERE cod_p= " + produto.getCodp() + ";";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                if (rs.first() == false) {
                    if (produto.getImagem() != null) {
                        File file = new File(produto.getImagem());
                        file.delete();
                    }
                    sql = "DELETE FROM Produto WHERE codp ='" + produto.getCodp() + "';";
                    stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                    stmt.close();
                    con.close();
                    listaProduto.remove(produto);
                    i = 1;
                    JOptionPane.showMessageDialog(null, "Item excluído!", "SUCESSO!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Não permitido excluir pois o produto está cadastrado em uma venda!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
                rs.close();
                stmt.close();
                con.close();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return i;
    }

    public ArrayList<Produto> getProduto(String nomeProduto) throws NotFoundItemException {
        listaProduto.removeAll(listaProduto);
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT* FROM Produto WHERE nome like '%" + nomeProduto + "%' ORDER BY nome;";
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                testeBanco = true;
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCodigo(rs.getInt("codigo"));
                produto.setValor(rs.getDouble("valor"));
                produto.setTotal();
                produto.setCodp(rs.getInt("codp"));
                produto.setImagem(rs.getString("imagem"));
                listaProduto.add(produto);
            }
            if (listaProduto.isEmpty()) {
                throw new NotFoundItemException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listaProduto;
    }

    public ArrayList<Produto> listarProdutos() throws EmptyBankException {
        testeBanco = false;
        listaProduto.removeAll(listaProduto);
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT* FROM Produto ORDER BY codigo;";
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                testeBanco = true;
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getInt("codigo"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValor(rs.getDouble("valor"));
                produto.setTotal();
                listaProduto.add(produto);
            }
            rs.close();
            stmt.close();
            conexao.close();
            if (!testeBanco) {
                throw new EmptyBankException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listaProduto;
    }

    public Produto getProdutoCodigo(int codigo) {
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sqlResult = "SELECT* FROM Produto WHERE codigo =" + codigo + ";";
            PreparedStatement stmt = conexao.prepareStatement(sqlResult);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCodigo(rs.getInt("codigo"));
                produto.setValor(rs.getDouble("valor"));
                produto.setTotal();
                produto.setCodp(rs.getInt("codp"));
                produto.setImagem(rs.getString("imagem"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return produto;
    }

    public ArrayList<Produto> getEstoque(int op) throws EmptyBankException {
        try {
            testeBanco = false;
            listaProduto.removeAll(listaProduto);
            if (op == 1) {
                Connection conexao = DriverManager.getConnection(url, user, senha);
                String sqlResult = "SELECT* FROM Produto WHERE quantidade>0 ORDER BY codigo;";
                PreparedStatement stmt = conexao.prepareStatement(sqlResult);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    testeBanco = true;
                    Produto produto = new Produto();
                    produto.setCodp(rs.getInt("codp"));
                    produto.setNome(rs.getString("nome"));
                    produto.setCodigo(rs.getInt("codigo"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setValor(rs.getDouble("valor"));
                    produto.setTotal();
                    listaProduto.add(produto);
                }
                rs.close();
                stmt.close();
                conexao.close();
            }
            if (op == 2) {
                testeBanco = false;
                Connection conexao = DriverManager.getConnection(url, user, senha);
                String sqlResult = "SELECT* FROM Produto WHERE quantidade=0 ORDER BY codigo;";
                PreparedStatement stmt = conexao.prepareStatement(sqlResult);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    testeBanco = true;
                    Produto produto = new Produto();
                    produto.setCodp(rs.getInt("codp"));
                    produto.setNome(rs.getString("nome"));
                    produto.setCodigo(rs.getInt("codigo"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setValor(rs.getDouble("valor"));
                    produto.setTotal();
                    listaProduto.add(produto);
                }
                rs.close();
                stmt.close();
                conexao.close();
            }
            if (!testeBanco) {
                throw new EmptyBankException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listaProduto;
    }

    public void setVenda(Venda compra) {
        listaProduto.removeAll(listaProduto);
        listaProduto = compra.getProdutosComprados();
        int i = 0;
        int cont = listaProduto.size();
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            while (cont != 0) {
                String update = "UPDATE Produto SET quantidade = quantidade-" + listaProduto.get(i).getQuantidade() + " WHERE codp=" + listaProduto.get(i).getCodp() + ";";
                PreparedStatement stmt = conexao.prepareStatement(update);
                stmt.executeUpdate();
                stmt.close();
                cont--;
                i++;
            }
            String sql = "INSERT INTO compra (cod_c,data,quantidade,total,parcelas) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, compra.getCodigoCliente());
            stmt.setDate(2, new java.sql.Date(compra.getData().getTime()));
            stmt.setInt(3, listaProduto.size());
            stmt.setDouble(4, compra.getTotal());
            stmt.setInt(5, compra.getParcelas());
            stmt.executeUpdate();
            stmt.close();
            sql = "SELECT Max(cod_com) as cod FROM compra";
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int codigoVenda = 0;
            while (rs.next()) {
                codigoVenda = rs.getInt("cod");
            }
            rs.close();
            stmt.close();
            i = 0;
            cont = listaProduto.size();
            while (cont != 0) {
                sql = "INSERT INTO produtosComprados (cod_com,cod_p,quantidade) VALUES (?,?,?)";
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, codigoVenda);
                stmt.setInt(2, listaProduto.get(i).getCodp());
                stmt.setInt(3, listaProduto.get(i).getQuantidade());
                stmt.executeUpdate();
                stmt.close();
                i++;
                cont--;
            }
            sql = "INSERT INTO AVISOS (data, cod_com,cod_c,nivel) VALUES (date_add(now(),INTERVAL 2 DAY)," + codigoVenda + "," + compra.getCodigoCliente() + ",1)";
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            sql = "INSERT INTO AVISOS (data, cod_com,cod_c,nivel) VALUES (date_add(now(),INTERVAL 2 WEEK)," + codigoVenda + "," + compra.getCodigoCliente() + ",2)";
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            sql = "INSERT INTO AVISOS (data, cod_com,cod_c,nivel) VALUES (date_add(now(),INTERVAL 2 MONTH)," + codigoVenda + "," + compra.getCodigoCliente() + ",3)";
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Venda realidada com sucesso!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Venda> getVenda(int indice, Object objeto) throws ParseException, NotFoundItemException {
        try {
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sql = null;
            listaVenda.removeAll(listaVenda);
            if (indice == 1) {
                try {
                    int codigo = Integer.parseInt(objeto.toString());
                    sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c where cod_com = " + codigo + ";";
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Você deve digitar um número no campo de código!", "ERRO!", JOptionPane.ERROR_MESSAGE);
                }

            } else if (indice == 2) {
                String d = objeto.toString();
                sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c WHERE data='" + d + "'";
            } else if (indice == 3) {
                String nome = objeto.toString();
                sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c where cli.nome like '%" + nome + "%'";
            } else {
                sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c";
            }
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venda vendas = new Venda();
                vendas.setCodigoVenda(rs.getInt("cod_com"));
                vendas.setCodigoCliente(rs.getInt("cod_c"));
                vendas.setTotal(rs.getDouble("total"));
                vendas.setData(rs.getDate("data"));
                vendas.setQuantidade(rs.getInt("quantidade"));
                vendas.setNomeCliente(rs.getString("nome"));
                vendas.setParcelas(rs.getInt("parcelas"));
                listaVenda.add(vendas);
            }
            rs.close();
            stmt.close();
            int i = 0;
            int size = listaVenda.size();
            while (size != 0) {
                ArrayList produtos = new <Produto> ArrayList();
                sql = "SELECT pc.quantidade,pc.cod_p,p.codigo, p.nome, p.valor FROM produtoscomprados pc INNER JOIN produto p ON p.codp = pc.cod_p WHERE cod_com=" + listaVenda.get(i).getCodigoVenda();
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setCodp(rs.getInt("cod_p"));
                    produto.setCodigo(rs.getInt("codigo"));
                    produto.setNome(rs.getString("nome"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setValor(rs.getDouble("valor"));
                    produto.setTotal();
                    produtos.add(produto);
                }
                listaVenda.get(i).setProdutosComprados(produtos);
                i++;
                size--;
            }
            conexao.close();
            if (listaVenda.isEmpty()) {
                throw new NotFoundItemException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listaVenda;
    }

    public ArrayList<Venda> listarVendas(int op, Date data1, Date data2) throws NotFoundItemException {
        try {
            listaVenda.removeAll(listaVenda);
            String sql = null;
            if (op < 3 || op == 6) {
                if (op == 1) {
                    sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c where c.data between '" + data1 + "' and '" + data2 + "' ";
                } else if (op == 2) {
                    sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c WHERE data='" + data1 + "'";
                } else if (op == 6) {
                    sql = "select c.cod_com, c.cod_c, c.data, c.total,parcelas, cli.nome, quantidade from compra c inner join cliente cli ON cli.codigo = c.cod_c";
                }
                Connection conexao = DriverManager.getConnection(url, user, senha);
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Venda vendas = new Venda();
                    vendas.setCodigoVenda(rs.getInt("cod_com"));
                    vendas.setCodigoCliente(rs.getInt("cod_c"));
                    vendas.setTotal(rs.getDouble("total"));
                    vendas.setData(rs.getDate("data"));
                    vendas.setQuantidade(rs.getInt("quantidade"));
                    vendas.setNomeCliente(rs.getString("nome"));
                    vendas.setParcelas(rs.getInt("parcelas"));
                    listaVenda.add(vendas);
                }
                rs.close();
                stmt.close();
                conexao.close();
            } else if (op == 3) {
                sql = "SELECT SUM(total) as total, EXTRACT(YEAR FROM data) as year, COUNT(cod_c) as num FROM compra GROUP BY EXTRACT(YEAR FROM data)";
                Connection conexao = DriverManager.getConnection(url, user, senha);
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Venda vendas = new Venda();
                    vendas.setQuantidade(rs.getInt("num"));
                    vendas.setTotal(rs.getDouble("total"));
                    vendas.setNomeCliente(rs.getString("year"));
                    listaVenda.add(vendas);
                }
                rs.close();
                stmt.close();
                conexao.close();
            } else if (op == 4) {
                sql = "SELECT SUM(total) as total, EXTRACT(YEAR FROM data) as year, COUNT(cod_c) as num, CASE MONTHNAME(data) "
                        + "WHEN 'January' THEN 'Janeiro' "
                        + "WHEN 'February' THEN 'Fevereiro' "
                        + "WHEN 'March' THEN 'Março' "
                        + "WHEN 'April' THEN 'Abril' "
                        + "WHEN 'May' THEN 'Maio' "
                        + "WHEN 'June' THEN 'Junho' "
                        + "WHEN 'July' THEN 'Julho' "
                        + "WHEN 'August' THEN 'Agosto' "
                        + "WHEN 'September' THEN 'Setembro' "
                        + "WHEN 'October' THEN 'Outubro'  "
                        + "WHEN 'November' THEN 'Novembro' "
                        + "WHEN 'December' THEN 'Dezembro' "
                        + "END as mes FROM compra GROUP BY EXTRACT(YEAR FROM data), MONTH(data)";
                Connection conexao = DriverManager.getConnection(url, user, senha);
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Venda vendas = new Venda();
                    vendas.setQuantidade(rs.getInt("num"));
                    vendas.setTotal(rs.getDouble("total"));
                    vendas.setCodigoVenda(rs.getInt("year"));
                    vendas.setNomeCliente(rs.getString("mes"));
                    listaVenda.add(vendas);
                }
                rs.close();
                stmt.close();
                conexao.close();
            } else if (op == 5) {
                sql = "select data, quantidade, total from compra where MONTH(data) = MONTH(now())";
                Connection conexao = DriverManager.getConnection(url, user, senha);
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Venda vendas = new Venda();
                    vendas.setQuantidade(rs.getInt("quantidade"));
                    vendas.setTotal(rs.getDouble("total"));
                    vendas.setData(rs.getDate("data"));
                    listaVenda.add(vendas);
                }
                rs.close();
                stmt.close();
                conexao.close();
            }
            if (listaVenda.isEmpty()) {
                throw new NotFoundItemException();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listaVenda;
    }

    public ArrayList<Cliente> getAniversarios(int op) {
        try {
            lista.removeAll(lista);
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sql = "";
            if (op == 1) {
                sql = "SELECT codigo,nome,email,telefone,celular,dataNascimento,date_format(dataNascimento,'%d/%m') as data FROM CLIENTE WHERE extract(month from dataNascimento)= month(now()) and extract(day from dataNascimento)= day(now());";
            }
            if (op == 2) {
                sql = "SELECT codigo,nome,email,telefone,celular,dataNascimento,date_format(dataNascimento,'%d/%m') as data FROM CLIENTE WHERE MONTH(dataNascimento) = MONTH(now()) ORDER BY dataNascimento;";
            }
            if (op == 3) {
                sql = "SELECT codigo,nome,email,telefone,celular,dataNascimento,date_format(dataNascimento,'%d/%m') as data FROM CLIENTE  ORDER BY dataNascimento";
            }
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNascimento(rs.getDate("dataNascimento"));
                cliente.setIdade();
                cliente.setBairro(rs.getString("data"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                lista.add(cliente);
            }
            stmt.close();
            rs.close();
            conexao.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lista;
    }

    public ArrayList<Aviso> getAvisos(int op) {
        try {
            listaAviso.removeAll(listaAviso);
            Connection conexao = DriverManager.getConnection(url, user, senha);
            String sql = "";
            if (op == 3) {
                sql = "DELETE FROM AVISOS WHERE MONTH(data)<MONTH(now())";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.execute();
                stmt.close();
            } else {
                if (op == 1) {
                    sql = "SELECT a.data, CASE a.nivel "
                            + "WHEN '1' THEN '2 DIAS' "
                            + "WHEN '2' THEN '2 SEMANAS' "
                            + "WHEN '3' THEN '2 MESES' "
                            + "END as nivel, "
                            + "c.data as data_compra, a.cod_com, a.cod_c,cli.nome from avisos a "
                            + "INNER JOIN Cliente cli on cli.codigo = a.cod_c "
                            + "INNER JOIN Compra c on c.cod_com = a.cod_com where a.data like '%now()%';";
                }
                if (op == 2) {
                    sql = "SELECT a.data, CASE a.nivel "
                            + "WHEN '1' THEN '2 DIAS' "
                            + "WHEN '2' THEN '2 SEMANAS' "
                            + "WHEN '3' THEN '2 MESES' "
                            + "END as nivel, "
                            + "c.data as data_compra, a.cod_com, a.cod_c,cli.nome from avisos a "
                            + "INNER JOIN Cliente cli on cli.codigo = a.cod_c "
                            + "INNER JOIN Compra c on c.cod_com = a.cod_com where MONTH(a.data) = MONTH(now()) order by a.data;";
                }
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Aviso aviso = new Aviso();
                    aviso.setCodigoCliente(rs.getInt("cod_c"));
                    aviso.setCodigoCompra(rs.getInt("cod_com"));
                    aviso.setDataAviso(rs.getDate("data"));
                    aviso.setDataCompra(rs.getDate("data_compra"));
                    aviso.setNivel(rs.getString("nivel"));
                    aviso.setNomeCliente(rs.getString("nome"));
                    listaAviso.add(aviso);
                }
                stmt.close();
                rs.close();
                conexao.close();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listaAviso;
    }

    public void criarBackupSql() throws IOException {

        String t = new File(".").getCanonicalPath();
        System.out.println(t);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyyHH:mm:ss");
        String data = formato.format(System.currentTimeMillis());
        data = data.replace("/", "");
        data = data.replace(":", "");
        data.trim();
        File file = new File("backups");
        if (!file.exists()) {
            file.mkdir();
        }
        String nome = "backup_" + data + ".sql";
        String[] commands = new String[]{"cmd", "/c", "\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump", "-u" + "root", "-p" + "656512", "projetofinal", ">", "backups\\" + nome};
        Process child = Runtime.getRuntime().exec(commands);
    }

    public boolean importarBackupSql(String caminho) throws IOException {
        boolean confirma = false;
        if (caminho.substring(caminho.lastIndexOf('.') + 1).equals("sql")) {
            String[] commands = new String[]{"cmd", "/c", "\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql", "-u" + "root", "-p" + "656512", "projetofinal", "<", caminho};
            Process child = Runtime.getRuntime().exec(commands);
            confirma = true;
        } else {
            JOptionPane.showMessageDialog(null, "Você deve importar um arquivo .sql da pasta de Backups!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        return confirma;
    }

    public ConexaoBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não conecto");
            Logger
                    .getLogger(CadastroCliente.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        listaProduto = new <Produto> ArrayList();
        lista = new <Cliente> ArrayList();
        listaVenda = new <Venda> ArrayList();
        cliente = new Cliente();
        usuario = new Usuario();
        produto = new Produto();
        venda = new Venda();
        listaAviso = new <Aviso>ArrayList();
    }
}
