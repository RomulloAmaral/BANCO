
import java.util.Scanner;

public class SistemaBancoUI {
    private Banco banco;
    private Scanner scanner;

    public SistemaBancoUI() {
        scanner = new Scanner(System.in);
        System.out.print("Digite o nome do Banco: ");
        String nomeBanco = scanner.nextLine();
        this.banco = new Banco(nomeBanco);
        System.out.println("Banco '" + nomeBanco + "' criado com sucesso!");
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    criarCliente();
                    break;
                case 2:
                    criarContaBancaria();
                    break;
                case 3:
                    adicionarFundos();
                    break;
                case 4:
                    efetuarSaque();
                    break;
                case 5:
                    realizarTransferencia();
                    break;
                case 6:
                    emitirExtrato();
                    break;
                case 7:
                    banco.listarContas();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        } while (opcao != 0);
        scanner.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n----- Menu Principal do " + banco.getNome() + " -----");
        System.out.println("1. Criar Cliente");
        System.out.println("2. Criar Conta Bancaria");
        System.out.println("3. Adicionar Fundos");
        System.out.println("4. Efetuar Saque");
        System.out.println("5. Realizar Transferencia");
        System.out.println("6. Emitir Extrato");
        System.out.println("7. Listar Contas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void criarCliente() {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Endereco do Cliente: ");
        String endereco = scanner.nextLine();
        Cliente cliente = new Cliente(nome, endereco);
        System.out.println("Cliente '" + cliente.getNome() + "' criado com sucesso!");
    }

    private void criarContaBancaria() {
        System.out.print("Nome do Cliente para a nova conta: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("Endereço do Cliente (se o cliente não existir, ele será criado): ");
        String enderecoCliente = scanner.nextLine();
        Cliente cliente = new Cliente(nomeCliente, enderecoCliente);

        System.out.print("Possui Crédito Especial? (sim/nao): ");
        boolean possuiCreditoEspecial = scanner.nextLine().equalsIgnoreCase("sim");

        ContaBancaria novaConta = new ContaBancaria(cliente, possuiCreditoEspecial);
        banco.adicionarConta(novaConta);
        System.out.println("Conta bancária para '" + cliente.getNome() + "' criada com sucesso!");
    }

    private ContaBancaria selecionarConta(String acao) {
        System.out.print("Digite o nome do cliente da conta para " + acao + ": ");
        String nomeCliente = scanner.nextLine();
        ContaBancaria conta = banco.buscarContaPorNomeCliente(nomeCliente);
        if (conta == null) {
            System.out.println("Conta do cliente '" + nomeCliente + "' não encontrada.");
        }
        return conta;
    }

    private void adicionarFundos() {
        ContaBancaria conta = selecionarConta("adicionar fundos");
        if (conta != null) {
            System.out.print("Valor para depositar: R$");
            double valor = lerValor();
            if (valor > 0) {
                conta.adicionarFundos(valor);
            } else {
                System.out.println("O valor do depósito deve ser positivo.");
            }
        }
    }

    private void efetuarSaque() {
        ContaBancaria conta = selecionarConta("sacar");
        if (conta != null) {
            System.out.print("Valor para sacar: R$");
            double valor = lerValor();
            if (valor > 0) {
                conta.efetuarSaque(valor);
            } else {
                System.out.println("O valor do saque deve ser positivo.");
            }
        }
    }

    private void realizarTransferencia() {
        ContaBancaria origem = selecionarConta("transferir (conta de origem)");
        if (origem != null) {
            System.out.print("Nome do cliente da conta de destino: ");
            String nomeDestino = scanner.nextLine();
            ContaBancaria destino = banco.buscarContaPorNomeCliente(nomeDestino);

            if (destino != null) {
                System.out.print("Valor para transferir: R$");
                double valor = lerValor();
                if (valor > 0) {
                    origem.realizarTransferencia(destino, valor);
                } else {
                    System.out.println("O valor da transferência deve ser positivo.");
                }
            } else {
                System.out.println("Conta de destino não encontrada.");
            }
        }
    }

    private void emitirExtrato() {
        ContaBancaria conta = selecionarConta("emitir extrato");
        if (conta != null) {
            conta.emitirExtrato();
        }
    }

    private double lerValor() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public static void main(String[] args) {
        SistemaBancoUI sistema = new SistemaBancoUI();
        sistema.iniciar();
    }
}