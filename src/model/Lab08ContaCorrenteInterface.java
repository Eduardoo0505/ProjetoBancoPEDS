package model;

import util.MyClassException;

public interface Lab08ContaCorrenteInterface {

    // Métodos para realizar operações financeiras
    void sacar(double valor) throws MyClassException;    // Realiza um saque na conta
    void depositar(double valor) throws MyClassException; // Realiza um depósito na conta

    // Métodos para gerenciar os dados da conta
    void recuperar() throws MyClassException;    // Recupera os dados da conta do arquivo
    boolean gravar() throws MyClassException;    // Grava os dados da conta no arquivo
    boolean removerArquivo() throws MyClassException; // Remove os arquivos associados à conta

    // Métodos de acesso e modificação (getters e setters)
    int getNumAge();                              // Retorna o número da agência
    void setNumAgencia(int numAge) throws MyClassException; // Configura o número da agência

    int getNumConta();                            // Retorna o número da conta
    void setNumConta(int numConta) throws MyClassException; // Configura o número da conta

    String getNome();                             // Retorna o nome do cliente
    void setNome(String nome);                    // Configura o nome do cliente

    double getSaldo();                            // Retorna o saldo da conta
    void setSaldo(double saldo);                  // Configura o saldo da conta

    // Método de exibição
    void imprimir();                              // Exibe os dados da conta
}