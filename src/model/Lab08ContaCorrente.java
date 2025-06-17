package model;

import util.MyClassException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Lab08ContaCorrente {
    private int numAge;      // Número da agência
    private int numConta;    // Número da conta
    private String nome;     // Nome do cliente
    private double saldo;    // Saldo da conta

    // Construtor completo
    public Lab08ContaCorrente(int numAge, int numConta, String nome, double saldo) throws MyClassException {
        setNumAgencia(numAge);
        setNumConta(numConta);
        this.nome = nome;
        this.saldo = saldo;
    }

    // Construtor com agência e conta apenas
    public Lab08ContaCorrente(int numAge, int numConta) throws MyClassException {
        setNumAgencia(numAge);
        setNumConta(numConta);
    }

    // Getters e setters com validação
    public int getNumAge() {
        return numAge;
    }

    public void setNumAgencia(int numAge) throws MyClassException {
        if (numAge < 0 || numAge > 9999) {
            throw new MyClassException("Agência inválida. Deve ser entre 0 e 9999.", "Lab08ContaCorrente", "setNumAgencia");
        }
        this.numAge = numAge;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) throws MyClassException {
        if (numConta < 0 || numConta > 9999999) {
            throw new MyClassException("Conta inválida. Deve ser entre 0 e 9999999.", "Lab08ContaCorrente", "setNumConta");
        }
        this.numConta = numConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método para saque
    public void sacar(double valor) throws MyClassException {
        if (valor <= 0) {
            throw new MyClassException("O valor do saque deve ser positivo.", "Lab08ContaCorrente", "sacar");
        }
        if (valor > saldo) {
            throw new MyClassException(
                String.format("Saldo insuficiente. Saldo disponível: R$ %.2f, valor solicitado: R$ %.2f", saldo, valor),
                "Lab08ContaCorrente",
                "sacar"
            );
        }
        saldo -= valor;
        System.out.printf("Saque realizado com sucesso. Novo saldo: R$ %.2f%n", saldo);
    }

    // Método para depósito
    public void depositar(double valor) throws MyClassException {
        if (valor <= 0) {
            throw new MyClassException("O valor do depósito deve ser positivo.", "Lab08ContaCorrente", "depositar");
        }
        saldo += valor;
        System.out.printf("Depósito realizado com sucesso. Novo saldo: R$ %.2f%n", saldo);
    }

    // Método para gravar os dados da conta
    public boolean gravar() throws MyClassException {
        try (FileWriter writer = new FileWriter(numAge + "." + numConta + ".dat")) {
            writer.write(numAge + "\n");
            writer.write(numConta + "\n");
            writer.write(nome + "\n");
            writer.write(saldo + "\n");
            System.out.printf("Dados gravados: Agência %04d, Conta %07d, Nome: %s, Saldo: R$ %.2f%n", numAge, numConta, nome, saldo);
            return true;
        } catch (IOException e) {
            throw new MyClassException(
                "Erro ao gravar os dados no arquivo.",
                "Lab08ContaCorrente",
                "gravar"
            );
        }
    }

    // Método para recuperar os dados da conta
    public void recuperar() throws MyClassException {
        try (BufferedReader reader = new BufferedReader(new FileReader(numAge + "." + numConta + ".dat"))) {
            numAge = Integer.parseInt(reader.readLine());
            numConta = Integer.parseInt(reader.readLine());
            nome = reader.readLine();
            saldo = Double.parseDouble(reader.readLine());
            System.out.printf("Dados recuperados: Agência %04d, Conta %07d, Nome: %s, Saldo: R$ %.2f%n", numAge, numConta, nome, saldo);
        } catch (IOException e) {
            throw new MyClassException(
                "Erro ao recuperar os dados do arquivo.",
                "Lab08ContaCorrente",
                "recuperar"
            );
        } catch (NumberFormatException e) {
            throw new MyClassException(
                "Erro no formato dos dados recuperados.",
                "Lab08ContaCorrente",
                "recuperar"
            );
        }
    }

    // Método para imprimir os dados da conta
    public void imprimir() {
        System.out.printf("Agência: %04d | Conta: %07d | Nome: %s | Saldo: R$ %.2f%n", numAge, numConta, nome, saldo);
    }

    // Método para remover o arquivo da conta
    public boolean removerArquivo() throws MyClassException {
        File arquivo = new File(numAge + "." + numConta + ".dat");
        if (arquivo.exists() && arquivo.delete()) {
            System.out.println("Conta removida com sucesso.");
            return true;
        } else {
            throw new MyClassException(
                "Erro ao remover o arquivo da conta.",
                "Lab08ContaCorrente",
                "removerArquivo"
            );
        }
    }
}