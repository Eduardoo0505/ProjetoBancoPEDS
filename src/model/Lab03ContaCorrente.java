package model;

import java.io.*;

public class Lab03ContaCorrente {
    private int numAge;
    private int numConta;
    private String nome;
    private double saldo;

    // Construtor com dois parâmetros
    public Lab03ContaCorrente(int numAge, int numConta) {
        this.numAge = numAge;
        this.numConta = numConta;
        recuperar(); // Recupera dados do arquivo ao criar
    }

    // Construtor com todos os parâmetros
    public Lab03ContaCorrente(int numAge, int numConta, String nome, double saldo) {
        this.numAge = numAge;
        this.numConta = numConta;
        this.nome = nome;
        this.saldo = saldo;
    }

    public int sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return 1; // Sucesso
        }
        return 0; // Falha
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public boolean gravar() {
        try {
            FileWriter fw = new FileWriter(numAge + "." + numConta + ".dat");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(numAge);
            pw.println(numConta);
            pw.println(nome);
            pw.println(saldo);
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void recuperar() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(numAge + "." + numConta + ".dat"));
            numAge = Integer.parseInt(br.readLine());
            numConta = Integer.parseInt(br.readLine());
            nome = br.readLine();
            saldo = Double.parseDouble(br.readLine());
            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao recuperar os dados.");
        }
    }

    public boolean removerArquivo() {
        File arquivo = new File(numAge + "." + numConta + ".dat");
        return arquivo.delete();
    }

    public void imprimir() {
        System.out.println("Agência: " + numAge);
        System.out.println("Conta: " + numConta);
        System.out.println("Nome: " + nome);
        System.out.println("Saldo: R$ " + saldo);
    }

    // Get e Set
    public int getNumAge() { return numAge; }
    public void setNumAge(int numAge) { this.numAge = numAge; }
    public int getNumConta() { return numConta; }
    public void setNumConta(int numConta) { this.numConta = numConta; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }


}