package com.serviceplataform.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Optional;

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
 * Tela de busca de serviço por nome (RF01 / RF04 - Opção 3).
 *
 * Diferente da tela de Cadastro: aqui só o campo "Nome" é editável. Descrição
 * e Valor são apenas exibição do resultado da busca (somente leitura),
 * preenchidos automaticamente quando o serviço é encontrado.
 */
public class PesquisarPanel extends JPanel {

    private final JTextField txtNome = new JTextField();
    private final JTextField txtDescricao = new JTextField();
    private final JTextField txtValor = new JTextField();

    public PesquisarPanel(App.Navegacao navegacao) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        txtDescricao.setEditable(false);
        txtValor.setEditable(false);

        add(criarCabecalho(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(navegacao), BorderLayout.SOUTH);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(0x0F, 0x4C, 0x81));
        cabecalho.setPreferredSize(new Dimension(0, 70));

        JLabel label = new JLabel("Buscar Serviço");
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
        gbc.insets = new Insets(20, 0, 8, 0);
        painel.add(criarRotulo("Resultado da busca:"), gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(8, 0, 8, 0);
        painel.add(criarRotulo("Descrição:"), gbc);
        gbc.gridy = 4;
        painel.add(txtDescricao, gbc);

        gbc.gridy = 5;
        painel.add(criarRotulo("Valor (R$):"), gbc);
        gbc.gridy = 6;
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

        JButton btnBuscar = new JButton("Buscar");
        estilizarBotao(btnBuscar, new Color(0x0F, 0x4C, 0x81));
        btnBuscar.addActionListener(e -> pesquisar());

        painel.add(btnVoltar);
        painel.add(btnBuscar);
        return painel;
    }

    private void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(120, 32));
    }

    private void pesquisar() {
        String nome = txtNome.getText();

        // Usamos isBlank() (e não == "") porque == compara referência de
        // objeto em Java, não o conteúdo da String — esse era o bug da
        // versão anterior em JavaFX.
        if (nome.isBlank()) {
            exibirAviso("Preencha o campo 'Busca'");
            return;
        }

        // CatalogoServicos.buscarPorNome() é quem implementa a regra de busca
        // (ver TODO na classe CatalogoServicos). Aqui só consumimos o resultado.
        Optional<Servico> resultado = CatalogoServicos.getInstancia().buscarPorNome(nome);

        if (resultado.isPresent()) {
            Servico servico = resultado.get();
            txtDescricao.setText(servico.getDescricao());
            txtValor.setText(String.format("%.2f", servico.getValor()));
        } else {
            txtDescricao.setText("");
            txtValor.setText("");
            exibirAviso("Serviço não encontrado: \"" + nome + "\"");
        }
    }

    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Busca de serviço", JOptionPane.WARNING_MESSAGE);
    }
}
