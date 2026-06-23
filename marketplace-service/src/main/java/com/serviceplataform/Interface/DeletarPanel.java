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

/**
 * Tela de remoção de serviço por nome (complementa o RF01 - gerenciamento
 * dos serviços disponíveis).
 */
public class DeletarPanel extends JPanel {

    private final JTextField txtNome = new JTextField();

    public DeletarPanel(App.Navegacao navegacao) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(criarCabecalho(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(navegacao), BorderLayout.SOUTH);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(0x0F, 0x4C, 0x81));
        cabecalho.setPreferredSize(new Dimension(0, 70));

        JLabel label = new JLabel("Remover Serviço");
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
        JLabel aviso = new JLabel("Informe o nome exato do serviço cadastrado para excluí-lo.");
        aviso.setFont(new Font("Arial", Font.PLAIN, 12));
        aviso.setForeground(new Color(0x90, 0x93, 0x93));
        painel.add(aviso, gbc);

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

        JButton btnOk = new JButton("Remover");
        estilizarBotao(btnOk, new Color(0xB0, 0x30, 0x30));
        btnOk.addActionListener(e -> deletar());

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

    private void deletar() {
        String nome = txtNome.getText();

        if (nome.isBlank()) {
            exibirAviso("Informe o nome do serviço a remover.");
            return;
        }

        // CatalogoServicos.removerPorNome() implementa a regra de remoção
        // (ver TODO na classe CatalogoServicos).
        boolean removido = CatalogoServicos.getInstancia().removerPorNome(nome);

        if (removido) {
            exibirInfo("Serviço \"" + nome + "\" removido com sucesso!");
            txtNome.setText("");
        } else {
            exibirAviso("Serviço não encontrado: \"" + nome + "\"");
        }
    }

    private void exibirInfo(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Remoção de serviço", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Remoção de serviço", JOptionPane.WARNING_MESSAGE);
    }
}
