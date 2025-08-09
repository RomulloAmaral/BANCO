public class Principal {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco JavaBrasil");

        Cliente cliente1 = new Cliente("Carlos", "Rua das Acácias, 123");
        Cliente cliente2 = new Cliente("Fernanda", "Av. Central, 456");

        ContaBancaria conta1 = new ContaBancaria(cliente1, false);
        ContaBancaria conta2 = new ContaBancaria(cliente2, true);

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);

        conta1.adicionarFundos(500);
        conta2.adicionarFundos(300);

        conta1.efetuarSaque(100);
        conta2.efetuarSaque(400);

        conta1.realizarTransferencia(conta2, 200);

        conta1.emitirExtrato();
        conta2.emitirExtrato();

        banco.listarContas();
    }
}
