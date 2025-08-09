// Banco.java - APENAS AS MODIFICAÇÕES/ADIÇÕES
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private List<ContaBancaria> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public void listarContas() {
        System.out.println("\nContas do banco " + nome + ":");

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        for (ContaBancaria conta : contas) {

            System.out.println("Cliente: " + conta.getCliente().getNome() + " | Saldo: R$" + String.format("%.2f", conta.getSaldo()));
        }
    }

    public ContaBancaria buscarContaPorNomeCliente(String nomeCliente) {
        for (ContaBancaria conta : contas) {
            if (conta.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                return conta;
            }
        }
        return null; // Conta não encontrada
    }

    public String getNome() {
        return nome;
    }
}