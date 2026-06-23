package com.serviceplataform.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.serviceplataform.App;
import com.serviceplataform.Pagamentos.Pagamento;
import com.serviceplataform.Pagamentos.PagamentoFactory;
import com.serviceplataform.Servicos.CatalogoServicos;
import com.serviceplataform.Servicos.Servico;

/**
 * Tela de realização de pedido (RF02, RF03 / RF04 - Opção 4).
 *
 * Implementa App.TelaAtualizavel: sempre que esta tela é exibida, o combo
 * de serviços é recarregado a partir de CatalogoServicos.listarTodos() —
 * assim, se um serviço for cadastrado ou removido em outra tela, o Pedido
 * sempre mostra a lista atualizada.
 *
 * O combo de formas de pagamento vem de PagamentoFactory.listarNomesDisponiveis():
 * quando a Etapa 2 registrar novas formas de pagamento na fábrica
 * (Criptomoeda, Carteira Digital, Débito), elas aparecem aqui automaticamente,
 * sem precisar alterar esta classe.
 */
public class PedidoPanel extends JPanel implements App.TelaAtualizavel {

    private final JTextField txtNomeCliente = new JTextField();
    private final JComboBox<Servico> comboServico = new JComboBox<>();
    private final JComboBox<String> comboPagamento = new JComboBox<>();
    private final JLabel lblValorTotal = new JLabel("Valor Total R$: --");

    public PedidoPanel(App.Navegacao navegacao) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        comboPagamento.setModel(new DefaultComboBoxModel<>(
                PagamentoFactory.listarNomesDisponiveis().toArray(new String[0])));

        comboServico.addActionListener(e -> atualizarValorTotal());
        comboPagamento.addActionListener(e -> atualizarValorTotal());

        add(criarCabecalho(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
        add(criarBotoes(navegacao), BorderLayout.SOUTH);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(0x0F, 0x4C, 0x81));
        cabecalho.setPreferredSize(new Dimension(0, 70));

        JLabel label = new JLabel("Realizar Pedido");
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
        painel.add(criarRotulo("Nome do cliente:"), gbc);
        gbc.gridy = 1;
        painel.add(txtNomeCliente, gbc);

        gbc.gridy = 2;
        painel.add(criarRotulo("Serviço:"), gbc);
        gbc.gridy = 3;
        painel.add(comboServico, gbc);

        gbc.gridy = 4;
        painel.add(criarRotulo("Forma de pagamento:"), gbc);
        gbc.gridy = 5;
        painel.add(comboPagamento, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(24, 0, 8, 0);
        lblValorTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblValorTotal.setForeground(new Color(0x0F, 0x4C, 0x81));
        painel.add(lblValorTotal, gbc);

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

        JButton btnConfirmar = new JButton("Confirmar Pedido");
        estilizarBotao(btnConfirmar, new Color(0x0F, 0x4C, 0x81));
        btnConfirmar.addActionListener(e -> confirmarPedido());

        painel.add(btnVoltar);
        painel.add(btnConfirmar);
        return painel;
    }

    private void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(150, 32));
    }

    private void atualizarValorTotal() {
        Servico servico = (Servico) comboServico.getSelectedItem();
        String formaPagamento = (String) comboPagamento.getSelectedItem();

        if (servico == null || formaPagamento == null) {
            lblValorTotal.setText("Valor Total R$: --");
            return;
        }

        // PagamentoFactory.criar() + Pagamento.calcularValorFinal() concentram
        // toda a regra de taxas (RF02) — esta tela só exibe o resultado.
        Pagamento pagamento = PagamentoFactory.criar(formaPagamento);
        double valorFinal = pagamento.calcularValorFinal(servico.getValor());

        lblValorTotal.setText(String.format("Valor Total R$: %.2f", valorFinal));
    }

    private void confirmarPedido() {
        String nomeCliente = txtNomeCliente.getText();
        Servico servico = (Servico) comboServico.getSelectedItem();
        String formaPagamentoNome = (String) comboPagamento.getSelectedItem();

        if (nomeCliente == null || nomeCliente.isBlank()) {
            exibirAviso("Informe o nome do cliente.");
            return;
        }
        if (servico == null) {
            exibirAviso("Cadastre um serviço antes de realizar um pedido.");
            return;
        }
        if (formaPagamentoNome == null) {
            exibirAviso("Selecione uma forma de pagamento.");
            return;
        }

        Pagamento pagamento = PagamentoFactory.criar(formaPagamentoNome);
        double valorOriginal = servico.getValor();
        double taxa = pagamento.calcularTaxa(valorOriginal);
        double valorFinal = pagamento.calcularValorFinal(valorOriginal);

        // TODO (integrante responsável pela lógica de pedidos): caso seja necessário
        // persistir o pedido (ex: em uma classe Pedido / lista de histórico),
        // este é o ponto exato para criar e salvar esse objeto antes de exibir o resumo.

        exibirResumoPedido(nomeCliente, servico, pagamento, valorOriginal, taxa, valorFinal);
    }

    private void exibirResumoPedido(String nomeCliente, Servico servico, Pagamento pagamento,
                                     double valorOriginal, double taxa, double valorFinal) {
        String resumo = String.format(
                "Cliente: %s%n" +
                "Serviço: %s%n" +
                "Valor Original: R$ %.2f%n" +
                "Forma de Pagamento: %s%n" +
                "Taxa Aplicada: R$ %.2f%n" +
                "Valor Final: R$ %.2f%n%n" +
                "Pedido realizado com sucesso!",
                nomeCliente, servico.getNome(), valorOriginal,
                pagamento.getNomeExibicao(), taxa, valorFinal
        );

        JOptionPane.showMessageDialog(this, resumo, "Resumo do Pedido", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Pedido incompleto", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void aoExibir() {
        Servico selecionadoAntes = (Servico) comboServico.getSelectedItem();
        comboServico.setModel(new DefaultComboBoxModel<>(
                CatalogoServicos.getInstancia().listarTodos().toArray(new Servico[0])));

        // tenta preservar a seleção anterior, se o serviço ainda existir no catálogo
        if (selecionadoAntes != null) {
            for (int i = 0; i < comboServico.getItemCount(); i++) {
                if (comboServico.getItemAt(i).getNome().equals(selecionadoAntes.getNome())) {
                    comboServico.setSelectedIndex(i);
                    break;
                }
            }
        }

        atualizarValorTotal();
    }
}
