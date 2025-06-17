package model;

public class Lab02ContaCorrente {
    private int numAge;
    private int numConta;
    private String nome;
    private double saldo;

    public Lab02ContaCorrente(int numAge, int numConta, String nome, double saldo) {
        this.numAge = numAge;
        this.numConta = numConta;
        this.nome = nome;
        this.saldo = saldo;
    }

    public int saque(double pValor) {
        if (pValor > 0 && pValor <= saldo) {
            saldo -= pValor;
            return 1; // Sucesso
        } else {
            return 0; // Saldo insuficiente ou valor inválido
        }
    }

    public void deposito(double pValor) {
        if (pValor > 0) {
            saldo += pValor;
        }
    }

    public void imprimir() {
        System.out.println("Número da Agência: " + numAge);
        System.out.println("Número da Conta: " + numConta);
        System.out.println("Nome do Cliente: " + nome);
        System.out.println("Saldo: " + saldo);
    }
}