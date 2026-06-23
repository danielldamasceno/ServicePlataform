package com.serviceplataform.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.serviceplataform.App;
import com.serviceplataform.Servicos.CatalogoServicos;
import com.serviceplataform.Servicos.Servico;

/**
 * Tela de listagem de serviços (RF01 / RF04 - Opção 1).
 *
 * Implementa App.TelaAtualizavel: sempre que esta tela é exibida (usuário
 * clica em "Listar Serviços" no menu), a tabela é recarregada automaticamente
 * a partir de CatalogoServicos.listarTodos() — não é necessário clicar em
 * "Atualizar" manualmente, embora o botão também esteja disponível.
 *
 * Esta tela já está totalmente funcional, pois depende apenas de
 * CatalogoServicos.listarTodos(). Quando o integrante responsável
 * implementar a lógica em CatalogoServicos, esta tela passará a exibir
 * os dados reais automaticamente, sem precisar de nenhuma alteração aqui.
 */
public class ListarPanel extends JPanel implements App.TelaAtualizavel {

    private final DefaultTableModel modeloTabela;
    private final JTable tabela;

    public ListarPanel(App.Navegacao navegacao) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Descrição", "Valor (R$)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(24);
        tabela.setFont(new Font("Arial", Font.PLAIN, 13));

        add(criarCabecalho(), BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(criarBotoes(navegacao), BorderLayout.SOUTH);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel();
        cabecalho.setBackground(new Color(0x0F, 0x4C, 0x81));
        cabecalho.setPreferredSize(new Dimension(0, 70));

        JLabel label = new JLabel("Serviços Cadastrados");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        cabecalho.add(label);

        return cabecalho;
    }

    private JPanel criarBotoes(App.Navegacao navegacao) {
        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        JButton btnAtualizar = new JButton("Atualizar");
        estilizarBotao(btnAtualizar, new Color(0x90, 0x93, 0x93));
        btnAtualizar.addActionListener(e -> carregarServicos());

        JButton btnVoltar = new JButton("Voltar");
        estilizarBotao(btnVoltar, new Color(0x0F, 0x4C, 0x81));
        btnVoltar.addActionListener(e -> navegacao.irPara(App.CARD_MENU));

        painel.add(btnAtualizar);
        painel.add(btnVoltar);
        return painel;
    }

    private void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(120, 32));
    }

    private void carregarServicos() {
        modeloTabela.setRowCount(0);
        for (Servico servico : CatalogoServicos.getInstancia().listarTodos()) {
            modeloTabela.addRow(new Object[]{
                    servico.getNome(),
                    servico.getDescricao(),
                    String.format("%.2f", servico.getValor())
            });
        }
    }

    @Override
    public void aoExibir() {
        carregarServicos();
    }
}
