package com.serviceplataform.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.serviceplataform.App;
import com.serviceplataform.Servicos.CatalogoServicos;
import com.serviceplataform.Servicos.Servico;

/**
 * Tela de cadastro de serviço (RF01 / RF04 - Opção 2).
 */
public class CadastrarPanel extends JPanel {

    private final JTextField txtNome = new JTextField();
    private final JTextField txtDescricao = new JTextField();
    private final JTextField txtValor = new JTextField();

    public CadastrarPanel(App.Navegacao navegacao) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(criarCabecalho("Cadastrar Serviço"), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(navegacao), BorderLayout.SOUTH);
    }

    private JPanel criarCabecalho(String titulo) {
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(0x0F, 0x4C, 0x81));
        cabecalho.setPreferredSize(new Dimension(0, 70));

        JLabel label = new JLabel(titulo);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        cabecalho.add(label);

        return cabecalho;
    }

    private JPanel criarFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        painel.add(criarRotulo("Nome do serviço:"), gbc);
        gbc.gridy = 1;
        painel.add(txtNome, gbc);

        gbc.gridy = 2;
        painel.add(criarRotulo("Descrição:"), gbc);
        gbc.gridy = 3;
        painel.add(txtDescricao, gbc);

        gbc.gridy = 4;
        painel.add(criarRotulo("Valor (R$):"), gbc);
        gbc.gridy = 5;
        painel.add(txtValor, gbc);

        return painel;
    }

    private JLabel criarRotulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(new Color(0x0F, 0x4C, 0x81));
        return label;
    }

    private JPanel criarBotoes(App.Navegacao navegacao) {
        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createEmptyBorder(0, 40, 30, 40));

        JButton btnVoltar = new JButton("Voltar");
        estilizarBotao(btnVoltar, new Color(0x90, 0x93, 0x93));
        btnVoltar.addActionListener(e -> navegacao.irPara(App.CARD_MENU));

        JButton btnOk = new JButton("Cadastrar");
        estilizarBotao(btnOk, new Color(0x0F, 0x4C, 0x81));
        btnOk.addActionListener(e -> cadastrar());

        painel.add(btnVoltar);
        painel.add(btnOk);
        return painel;
    }

    private void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(120, 32));
    }

    private void cadastrar() {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        String valorTexto = txtValor.getText();

        double valor;
        try {
            // TODO (integrante responsável pela lógica): caso o formato de entrada
            // mude (ex: aceitar "R$ 150,00" com vírgula), ajustar o parsing aqui.
            valor = Double.parseDouble(valorTexto.replace(",", "."));
        } catch (NumberFormatException e) {
            exibirErro("Valor inválido. Informe um número, ex: 150.00");
            return;
        }

        try {
            // Servico() e CatalogoServicos.cadastrar() validam as regras de negócio
            // (nome não vazio, valor não negativo) — ver TODOs nessas classes.
            Servico novoServico = new Servico(nome, descricao, valor);
            CatalogoServicos.getInstancia().cadastrar(novoServico);

            exibirSucesso("Serviço \"" + nome + "\" cadastrado com sucesso!");
            limparCampos();
        } catch (IllegalArgumentException e) {
            exibirErro(e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtValor.setText("");
    }

    private void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
    }

    private void exibirSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
