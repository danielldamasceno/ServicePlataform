package com.serviceplataform;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.serviceplataform.Interface.CadastrarPanel;
import com.serviceplataform.Interface.DeletarPanel;
import com.serviceplataform.Interface.ListarPanel;
import com.serviceplataform.Interface.MenuPanel;
import com.serviceplataform.Interface.PedidoPanel;
import com.serviceplataform.Interface.PesquisarPanel;

/**
 * Janela principal
 */
public class App extends JFrame {

    public static final String CARD_MENU = "MENU";
    public static final String CARD_CADASTRAR = "CADASTRAR";
    public static final String CARD_PESQUISAR = "PESQUISAR";
    public static final String CARD_LISTAR = "LISTAR";
    public static final String CARD_DELETAR = "DELETAR";
    public static final String CARD_PEDIDO = "PEDIDO";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardContainer = new JPanel(cardLayout);
    private final Map<String, JPanel> telas = new LinkedHashMap<>();

    public App() {
        super("Service Marketplace");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(472, 660);
        setMinimumSize(new Dimension(472, 600));
        setLocationRelativeTo(null);

        Navegacao navegacao = this::irPara;

        registrarTela(CARD_MENU, new MenuPanel(navegacao));
        registrarTela(CARD_CADASTRAR, new CadastrarPanel(navegacao));
        registrarTela(CARD_PESQUISAR, new PesquisarPanel(navegacao));
        registrarTela(CARD_LISTAR, new ListarPanel(navegacao));
        registrarTela(CARD_DELETAR, new DeletarPanel(navegacao));
        registrarTela(CARD_PEDIDO, new PedidoPanel(navegacao));

        setContentPane(cardContainer);

        irPara(CARD_MENU);
    }

    private void registrarTela(String nomeCard, JPanel painel) {
        telas.put(nomeCard, painel);
        cardContainer.add(painel, nomeCard);
    }

    private void irPara(String nomeCard) {
        JPanel destino = telas.get(nomeCard);
        if (destino instanceof TelaAtualizavel) {
            ((TelaAtualizavel) destino).aoExibir();
        }
        cardLayout.show(cardContainer, nomeCard);
    }

    @FunctionalInterface
    public interface Navegacao {
        void irPara(String nomeCard);
    }


    public interface TelaAtualizavel {
        void aoExibir();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // se o look and feel do sistema não estiver disponível, segue com o padrão
            }
            new App().setVisible(true);
        });
    }
}
