package model;

public class Lab07ContaRemunerada extends Lab05ContaCorrenteEspecial implements Lab07ContaCorrenteInterface {

    private static final double TAXA_JUROS = 5.0; // Taxa de juros fixa (5%)

    // Construtor completo
    public Lab07ContaRemunerada(int numAge, int numConta, String nome, double saldo, double limite) {
        super(numAge, numConta, nome, saldo, limite);
    }

    // Construtor que recebe apenas agência e conta
    public Lab07ContaRemunerada(int numAge, int numConta) {
        super(numAge, numConta);
    }

    // Implementação do método para depósito
    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.printf("Depósito realizado com sucesso. Novo saldo: R$ %.2f%n", getSaldo());
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    // Implementação do método para saque
    @Override
    public int sacar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
            return -1; // Indica falha por valor inválido
        }
        if (getSaldo() - valor >= -getLimite()) {
            setSaldo(getSaldo() - valor);
            System.out.printf("Saque realizado com sucesso. Novo saldo: R$ %.2f%n", getSaldo());
            return 0; // Sucesso
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return -2; // Indica falha por saldo insuficiente
        }
    }

    // Implementação do método para cálculo de juros
    @Override
    public void calcularJuros() {
        if (getSaldo() >= 0) {
            double juros = Math.round(getSaldo() * (TAXA_JUROS / 100) * 100.0) / 100.0; // Arredonda para 2 casas decimais
            setSaldo(getSaldo() + juros);
            System.out.printf("Juros de R$ %.2f aplicados. Novo saldo: R$ %.2f%n", juros, getSaldo());
        } else {
            System.out.println("Não é possível calcular juros em saldos negativos.");
        }
    }
}