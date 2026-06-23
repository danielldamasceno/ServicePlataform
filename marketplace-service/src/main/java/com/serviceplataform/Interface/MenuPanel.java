package com.serviceplataform.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.serviceplataform.App;

/**
 * Tela de menu principal RF04.
 */
public class MenuPanel extends JPanel {

    public MenuPanel(App.Navegacao navegacao) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(criarCabecalho(), BorderLayout.NORTH);
        add(criarPainelBotoes(navegacao), BorderLayout.CENTER);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel();
        cabecalho.setLayout(new BorderLayout());
        cabecalho.setBackground(new Color(0x0F, 0x4C, 0x81));
        cabecalho.setPreferredSize(new Dimension(0, 130));

        JLabel titulo = new JLabel("Service Marketplace");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        cabecalho.add(titulo, BorderLayout.CENTER);

        java.net.URL imagemUrl = getClass().getResource("/images/Menu.png");
        if (imagemUrl != null) {
            ImageIcon icone = new ImageIcon(imagemUrl);
            Image escalada = icone.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            JLabel imagemLabel = new JLabel(new ImageIcon(escalada));
            imagemLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cabecalho.add(imagemLabel, BorderLayout.SOUTH);
        }

        return cabecalho;
    }

    private JPanel criarPainelBotoes(App.Navegacao navegacao) {
        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setLayout(new GridLayout(6, 1, 0, 14));
        painel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        painel.add(criarBotaoMenu("1 - Listar Serviços", () -> navegacao.irPara(App.CARD_LISTAR)));
        painel.add(criarBotaoMenu("2 - Cadastrar Serviço", () -> navegacao.irPara(App.CARD_CADASTRAR)));
        painel.add(criarBotaoMenu("3 - Buscar Serviço", () -> navegacao.irPara(App.CARD_PESQUISAR)));
        painel.add(criarBotaoMenu("4 - Realizar Pedido", () -> navegacao.irPara(App.CARD_PEDIDO)));
        painel.add(criarBotaoMenu("Remover Serviço", () -> navegacao.irPara(App.CARD_DELETAR)));
        painel.add(criarBotaoMenu("5 - Sair", () -> System.exit(0)));

        return painel;
    }

    private JButton criarBotaoMenu(String texto, Runnable acao) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 15));
        botao.setBackground(new Color(0xB3, 0xB3, 0xB3));
        botao.setFocusPainted(false);
        botao.addActionListener(e -> acao.run());
        return botao;
    }
}
