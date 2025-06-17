package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Lab05ContaCorrenteEspecial extends Lab03ContaCorrente {
    private double limite; // Limite de crédito adicional

    // Construtor completo com todos os atributos
    public Lab05ContaCorrenteEspecial(int numAge, int numConta, String nome, double saldo, double limite) {
        super(numAge, numConta, nome, saldo); // Chamada ao construtor da superclasse
        this.limite = limite;
    }

    // Construtor que recebe apenas agência e conta
    public Lab05ContaCorrenteEspecial(int numAge, int numConta) {
        super(numAge, numConta); // Chamada ao construtor da superclasse
    }

    // Método sobrescrito para considerar limite no saque
    @Override
    public int sacar(double valor) {
        if (getSaldo() + limite >= valor) { // Considerando limite no cálculo
            setSaldo(getSaldo() - valor); // Permite saldo negativo até o limite
            return 1; // Sucesso no saque
        }
        return 0; // Falha no saque (saldo insuficiente)
    }

    // Método sobrescrito para remover o arquivo .esp
    @Override
    public boolean removerArquivo() {
        super.removerArquivo(); // Chama método da superclasse
        File arquivoLimite = new File(getNumAge() + "." + getNumConta() + ".esp");
        return arquivoLimite.delete(); // Remove o arquivo de limite
    }

    // Método sobrescrito para imprimir saldo e limite
    @Override
    public void imprimir() {
        super.imprimir(); // Chama método da superclasse
        System.out.printf("Limite de crédito: R$ %.2f%n", limite);
    }

    // Método para gravar limite de crédito em arquivo separado
    public void gravarLimite() {
        File arquivoLimite = new File(getNumAge() + "." + getNumConta() + ".esp");
        try (FileWriter writer = new FileWriter(arquivoLimite)) {
            writer.write(String.valueOf(limite)); // Grava o valor do limite no arquivo
            System.out.println("Limite gravado com sucesso no arquivo: " + arquivoLimite.getName());
        } catch (IOException e) {
            System.out.println("Erro ao gravar limite: " + e.getMessage());
        }
    }

    // Métodos de acesso ao limite
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}