import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {
    private Cliente cliente;
    private double saldo;
    private boolean possuiCreditoEspecial;
    private List<String> extrato;

    public ContaBancaria(Cliente cliente, boolean possuiCreditoEspecial) {
        this.cliente = cliente;
        this.possuiCreditoEspecial = possuiCreditoEspecial;
        this.saldo = 0;
        this.extrato = new ArrayList<>();
    }

    public void adicionarFundos(double valor) {
        if (valor > 0) {
            saldo += valor;
            extrato.add("Depósito: +R$" + valor);
            System.out.println(cliente.getNome() + " recebeu depósito de R$" + valor);
        }
    }

    public boolean efetuarSaque(double valor) {
        if (valor <= saldo || possuiCreditoEspecial) {
            saldo -= valor;
            extrato.add("Saque: -R$" + valor);
            System.out.println(cliente.getNome() + " sacou R$" + valor);
            return true;
        } else {
            System.out.println("Saque negado para " + cliente.getNome() + ": saldo insuficiente.");
            return false;
        }
    }

    public boolean realizarTransferencia(ContaBancaria destino, double valor) {
        if (valor <= saldo || possuiCreditoEspecial) {
            saldo -= valor;
            destino.adicionarFundos(valor);
            extrato.add("Transferência para " + destino.getCliente().getNome() + ": -R$" + valor);
            System.out.println(cliente.getNome() + " transferiu R$" + valor + " para " + destino.getCliente().getNome());
            return true;
        } else {
            System.out.println("Transferência negada para " + cliente.getNome() + ": saldo insuficiente.");
            return false;
        }
    }

    public void emitirExtrato() {
        System.out.println("\nExtrato da conta de " + cliente.getNome() + ":");
        for (String linha : extrato) {
            System.out.println("- " + linha);
        }
        System.out.printf("Saldo atual: R$%.2f\n", saldo);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }
}
