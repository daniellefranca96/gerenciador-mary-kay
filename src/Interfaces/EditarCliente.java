/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Actions.Verificacao;
import Exceptions.CpfCadastrado;
import Exceptions.EmailInvalido;
import Exceptions.EmptyBankException;
import Exceptions.EmptyCampException;
import Exceptions.NotFoundItemException;
import JavaBeans.Cliente;
import JavaBeans.ConexaoBD;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Danielle S. França
 */
public class EditarCliente extends javax.swing.JFrame {

    //problemas ao setar a data e a idade
    Cliente clienteProcurado;
    String cpfInicial;
    DateFormat df = DateFormat.getDateInstance();
    JFrame parent;

    public EditarCliente(Cliente clienteProcurado, JFrame p) {
        this.parent = p;
        this.clienteProcurado = clienteProcurado;
        initComponents();
        preencherCampos();
        setLocationRelativeTo(null);
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void preencherCampos() {
        tnome.setText(clienteProcurado.getNome());
        tcpf.setText(clienteProcurado.getCpf());
        tidade.setText(Integer.toString(clienteProcurado.getIdade()));
        tdataNascimento.setDate(clienteProcurado.getDataNascimento());
        tcidade.setText(clienteProcurado.getCidade());
        tbairro.setText(clienteProcurado.getBairro());
        tendereco.setText(clienteProcurado.getEndereco());
        temail.setText(clienteProcurado.getEmail());
        ttelefone.setText(clienteProcurado.getTelefone());
        tcelular.setText(clienteProcurado.getCelular());
        tobservacoes.setText(clienteProcurado.getObservacoes());
        cpfInicial = clienteProcurado.getCpf();
        tdataNascimento.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("date".equals(e.getPropertyName())) {
                            clienteProcurado.setDataNascimento(tdataNascimento.getDate());
                            clienteProcurado.setIdade();
                            tidade.setText(Integer.toString(clienteProcurado.getIdade()));
                        }
                    }
                });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nome = new javax.swing.JLabel();
        tnome = new javax.swing.JTextField();
        endereco = new javax.swing.JLabel();
        tendereco = new javax.swing.JTextField();
        cidade = new javax.swing.JLabel();
        tcidade = new javax.swing.JTextField();
        bairro = new javax.swing.JLabel();
        tbairro = new javax.swing.JTextField();
        telefone = new javax.swing.JLabel();
        ttelefone = new javax.swing.JFormattedTextField();
        celular = new javax.swing.JLabel();
        tcelular = new javax.swing.JFormattedTextField();
        cpf = new javax.swing.JLabel();
        tcpf = new javax.swing.JFormattedTextField();
        email = new javax.swing.JLabel();
        temail = new javax.swing.JTextField();
        observacoes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tobservacoes = new javax.swing.JTextArea();
        dataNascimento = new javax.swing.JLabel();
        idade = new javax.swing.JLabel();
        tidade = new javax.swing.JLabel();
        tdataNascimento = new com.toedter.calendar.JDateChooser();
        salvar = new javax.swing.JButton();
        voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Cliente");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(153, 255, 255));
        jPanel1.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        nome.setText("Nome:");

        endereco.setText("Endereço:");

        tendereco.setText(" ");

        cidade.setText("Cidade:");

        bairro.setText("Bairro:");

        telefone.setText("Telefone:");

        try {
            ttelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        celular.setText("Celular:");

        try {
            tcelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cpf.setText("CPF:");

        try {
            tcpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        email.setText("E-mail:");

        observacoes.setText("Observações:");

        tobservacoes.setColumns(20);
        tobservacoes.setRows(5);
        jScrollPane1.setViewportView(tobservacoes);

        dataNascimento.setText("Data de Nascimento:");

        idade.setText("Idade:");

        tdataNascimento.setBackground(new java.awt.Color(255, 255, 255));
        tdataNascimento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tdataNascimentoPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(endereco)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tendereco, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bairro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(tbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(66, 66, 66))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(idade)
                                        .addGap(18, 18, 18)
                                        .addComponent(tidade)
                                        .addGap(174, 174, 174))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefone)
                            .addComponent(email))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ttelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(celular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tcelular)
                                .addGap(72, 72, 72))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(157, 157, 157))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nome)
                                .addGap(18, 18, 18)
                                .addComponent(tnome, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(cpf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tcpf, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dataNascimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tdataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tcidade, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(observacoes)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome)
                    .addComponent(tnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cpf)
                    .addComponent(tcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dataNascimento)
                        .addComponent(tidade))
                    .addComponent(tdataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idade))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cidade)
                    .addComponent(tcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bairro)
                    .addComponent(tbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endereco)
                    .addComponent(tendereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefone)
                    .addComponent(ttelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celular)
                    .addComponent(tcelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(temail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(observacoes)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        salvar.setText("Editar");
        salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salvar)
                .addGap(27, 27, 27)
                .addComponent(voltar)
                .addGap(214, 214, 214))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvar)
                    .addComponent(voltar))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarActionPerformed
        try {
            clienteProcurado.setNome(tnome.getText());
            if (tdataNascimento.getDate() == null) {
                java.util.Date data = null;
                clienteProcurado.setDataNascimento(data);
            } else {
                clienteProcurado.setDataNascimento(tdataNascimento.getDate());
            }
            Verificacao verificar = new Verificacao();
            if (!cpfInicial.equals(tcpf.getText())) {
                clienteProcurado.setCpf(tcpf.getText());
                verificar.verificarCpf(tcpf.getText());
            }
            if (!temail.getText().equals("")) {
                verificar.verificarEmail(temail.getText());
                clienteProcurado.setEmail(temail.getText());
            }
            int res = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja alterar esses dados?", "Aviso", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                clienteProcurado.setEndereco(tendereco.getText());
                clienteProcurado.setBairro(tbairro.getText());
                clienteProcurado.setCidade(tcidade.getText());
                clienteProcurado.setObservacoes(tobservacoes.getText());
                clienteProcurado.setTelefone(ttelefone.getText());
                clienteProcurado.setCelular(tcelular.getText());
                ConexaoBD conexao = new ConexaoBD();
                conexao.setCliente(2, clienteProcurado);
                if (parent != null) {
                    try {
                        Pesquisar pesquisar = (Pesquisar) parent;
                        pesquisar.buscar();
                    } catch (NotFoundItemException ex) {
                    } catch (EmptyBankException ex) {
                    }
                }
            }

        } catch (EmptyCampException ex) {
            JOptionPane.showMessageDialog(null, "Você deve preencher os campos NOME, CPF e DATA DE NASCIMENTO para realizar o cadastro!", "ERRO", JOptionPane.ERROR_MESSAGE);
        } catch (CpfCadastrado ex) {
            JOptionPane.showMessageDialog(null, "Esse CPF já está cadastrado no sistema!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        } catch (EmailInvalido ex) {
            JOptionPane.showMessageDialog(null, "Digite um e-mail válido!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_salvarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_voltarActionPerformed

    private void tdataNascimentoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tdataNascimentoPropertyChange
    }//GEN-LAST:event_tdataNascimentoPropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bairro;
    private javax.swing.JLabel celular;
    private javax.swing.JLabel cidade;
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel dataNascimento;
    private javax.swing.JLabel email;
    private javax.swing.JLabel endereco;
    private javax.swing.ButtonGroup gSexo;
    private javax.swing.JLabel idade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel observacoes;
    private javax.swing.JButton salvar;
    private javax.swing.JTextField tbairro;
    private javax.swing.JFormattedTextField tcelular;
    private javax.swing.JTextField tcidade;
    private javax.swing.JFormattedTextField tcpf;
    private com.toedter.calendar.JDateChooser tdataNascimento;
    private javax.swing.JLabel telefone;
    private javax.swing.JTextField temail;
    private javax.swing.JTextField tendereco;
    private javax.swing.JLabel tidade;
    private javax.swing.JTextField tnome;
    private javax.swing.JTextArea tobservacoes;
    private javax.swing.JFormattedTextField ttelefone;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
