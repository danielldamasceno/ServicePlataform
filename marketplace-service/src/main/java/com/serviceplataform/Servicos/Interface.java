import java.util.List;

public interface ServicoRepository {
    void cadastrar(Servico servico);
    List<Servico> listarTodos();
    Servico buscarPorNome(String nome);
    void exibirDetalhes(String nome);
}