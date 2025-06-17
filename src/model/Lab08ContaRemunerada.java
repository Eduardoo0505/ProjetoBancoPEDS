package model;

import util.MyClassException;

public class Lab08ContaRemunerada extends Lab08ContaCorrenteEspecial {

    private static final double TAXA_JUROS = 0.05; // Taxa de juros fixa (5%)

    // Construtor completo
    public Lab08ContaRemunerada(int numAge, int numConta, String nome, double saldo, double limite) throws MyClassException {
        super(numAge, numConta, nome, saldo, limite);
    }

    // Construtor com apenas agência e conta
    public Lab08ContaRemunerada(int numAge, int numConta) throws MyClassException {
        super(numAge, numConta);
    }

    // Método para calcular juros
    public void calcularJuros() throws MyClassException {
        if (getSaldo() >= 0) { // Apenas saldos positivos têm juros aplicáveis
            double juros = Math.round(getSaldo() * TAXA_JUROS * 100.0) / 100.0; // Calcula e arredonda os juros
            setSaldo(getSaldo() + juros);
            System.out.printf("Juros de R$ %.2f aplicados. Novo saldo: R$ %.2f%n", juros, getSaldo());
        } else {
            throw new MyClassException(
                "Não é possível calcular juros para saldo negativo.",
                this.getClass().getName(),
                "calcularJuros"
            );
        }
    }

    // Método sobrescrito para saque com suporte ao limite
    @Override
    public void sacar(double valor) throws MyClassException {
        if (valor <= 0) {
            throw new MyClassException("O valor do saque deve ser positivo.", "Lab08ContaRemunerada", "sacar");
        }
        if (valor > getSaldo() + getLimite()) {
            throw new MyClassException(
                "Saldo insuficiente para realizar o saque, considerando o limite.",
                "Lab08ContaRemunerada",
                "sacar"
            );
        }
        setSaldo(getSaldo() - valor);
        System.out.printf("Saque realizado com sucesso. Novo saldo: R$ %.2f%n", getSaldo());
    }

    // Método sobrescrito para gravar dados com suporte ao limite
    @Override
    public boolean gravar() throws MyClassException {
        try {
            super.gravar(); // Chama o método da superclasse para gravar dados comuns e limite
            return true;
        } catch (MyClassException e) {
            throw new MyClassException(
                "Erro ao gravar os dados da conta remunerada.",
                this.getClass().getName(),
                "gravar"
            );
        }
    }

    // Método sobrescrito para recuperar dados com suporte ao limite
    @Override
    public void recuperar() throws MyClassException {
        try {
            super.recuperar(); // Recupera os dados comuns e limite
        } catch (MyClassException e) {
            throw new MyClassException(
                "Erro ao recuperar os dados da conta remunerada.",
                this.getClass().getName(),
                "recuperar"
            );
        }
    }

    // Método sobrescrito para remover o arquivo da conta
    @Override
    public boolean removerArquivo() throws MyClassException {
        try {
            return super.removerArquivo(); // Remove o arquivo principal e o arquivo de limite
        } catch (MyClassException e) {
            throw new MyClassException(
                "Erro ao remover a conta remunerada.",
                this.getClass().getName(),
                "removerArquivo"
            );
        }
    }
}