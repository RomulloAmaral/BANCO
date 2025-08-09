
public class TestesBanco {

    public void executarTestes() {
        System.out.println("Iniciando testes automatizados do Sistema Banco...");

        System.out.println("\n--- Teste 1: Criação de Banco, Clientes e Contas ---");
        Banco bancoTeste = new Banco("Banco de Teste QA");
        System.out.println("Banco '" + bancoTeste.getNome() + "' criado.");

        Cliente cliente1 = new Cliente("Alice", "Rua A, 1");
        Cliente cliente2 = new Cliente("Bob", "Av. B, 2");
        System.out.println("Clientes '"+ cliente1.getNome() +"' e '"+ cliente2.getNome() +"' criados.");

        ContaBancaria conta1 = new ContaBancaria(cliente1, false);
        ContaBancaria conta2 = new ContaBancaria(cliente2, true);
        bancoTeste.adicionarConta(conta1);
        bancoTeste.adicionarConta(conta2);
        System.out.println("Contas para '"+ cliente1.getNome() +"' e '"+ cliente2.getNome() +"' criadas e adicionadas ao banco.");
        bancoTeste.listarContas();

        System.out.println("\n--- Teste 2: Adicionar Fundos ---");
        conta1.adicionarFundos(500.0);
        conta2.adicionarFundos(300.0);
        assertD(conta1.getSaldo(), 500.0, "Erro no saldo de Alice após depósito");
        assertD(conta2.getSaldo(), 300.0, "Erro no saldo de Bob após depósito");

        System.out.println("\n--- Teste 3: Efetuar Saque ---");
        conta1.efetuarSaque(100.0);
        assertD(conta1.getSaldo(), 400.0, "Erro no saldo de Alice após saque");

        conta2.efetuarSaque(400.0);
        assertD(conta2.getSaldo(), -100.0, "Erro no saldo de Bob após saque com crédito especial");

        System.out.println("\n--- Teste 3.1: Saque Negado ---");
        boolean saqueNegadoAlice = conta1.efetuarSaque(500.0);
        assertB(!saqueNegadoAlice, "Saque de Alice deveria ter sido negado");
        assertD(conta1.getSaldo(), 400.0, "Saldo de Alice não deveria ter mudado após saque negado");


        System.out.println("\n--- Teste 4: Realizar Transferência ---");
        conta1.realizarTransferencia(conta2, 200.0);
        assertD(conta1.getSaldo(), 200.0, "Erro no saldo de Alice após transferência");
        assertD(conta2.getSaldo(), 100.0, "Erro no saldo de Bob após transferência (recebeu depósito)");

        System.out.println("\n--- Teste 5: Emitir Extrato ---");
        conta1.emitirExtrato();
        conta2.emitirExtrato();

        System.out.println("\n--- Teste 6: Listar Contas do Banco ---");
        bancoTeste.listarContas();

        System.out.println("\nTodos os testes automatizados concluídos.");
    }

    private void assertD(double actual, double expected, String message) {
        if (Math.abs(actual - expected) < 0.001) {
            System.out.println("SUCESSO: " + message + " (Esperado: " + expected + ", Encontrado: " + actual + ")");
        } else {
            System.err.println("FALHA: " + message + " (Esperado: " + expected + ", Encontrado: " + actual + ")");
        }
    }

    private void assertB(boolean actual, String message) {
        if (actual) {
            System.out.println("SUCESSO: " + message);
        } else {
            System.err.println("FALHA: " + message);
        }
    }

    public static void main(String[] args) {
        new TestesBanco().executarTestes();
    }
}