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
            String mensagem = String.format("Depósito: +R$%.2f", valor);
            extrato.add(mensagem);
            System.out.println(String.format("%s recebeu depósito de R$%.2f", cliente.getNome(), valor));
        } else {
            System.out.println("Valor de depósito inválido. Deve ser maior que zero.");
        }
    }

    public boolean efetuarSaque(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido. Deve ser maior que zero.");
            return false;
        }

        if (valor <= saldo || possuiCreditoEspecial) {
            saldo -= valor;
            String mensagem = String.format("Saque: -R$%.2f", valor);
            extrato.add(mensagem);
            System.out.println(String.format("%s sacou R$%.2f", cliente.getNome(), valor));
            return true;
        } else {
            System.out.println(String.format("Saque negado para %s: saldo insuficiente.", cliente.getNome()));
            return false;
        }
    }

    public boolean realizarTransferencia(ContaBancaria destino, double valor) {
        if (valor <= 0) {
            System.out.println("Valor de transferência inválido. Deve ser maior que zero.");
            return false;
        }

        if (valor <= saldo || possuiCreditoEspecial) {
            saldo -= valor;
            destino.adicionarFundos(valor);
            String mensagem = String.format("Transferência para %s: -R$%.2f", destino.getCliente().getNome(), valor);
            extrato.add(mensagem);
            System.out.println(String.format("%s transferiu R$%.2f para %s", cliente.getNome(), valor, destino.getCliente().getNome()));
            return true;
        } else {
            System.out.println(String.format("Transferência negada para %s: saldo insuficiente.", cliente.getNome()));
            return false;
        }
    }

    public void emitirExtrato() {
        System.out.println("\n----- Extrato da conta de " + cliente.getNome() + " -----");
        for (String linha : extrato) {
            System.out.println("- " + linha);
        }
        System.out.println("-------------------------------");
        System.out.printf("Saldo atual: R$%.2f\n", saldo);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }
}
