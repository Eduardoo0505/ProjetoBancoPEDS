package view;

import model.Lab03ContaCorrente;
import model.Lab05ContaCorrenteEspecial;
import model.Lab04Historico;
import java.util.Scanner;

public class Lab06Sistema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("BEM-VINDO AO SEU BANCO PEDS");
            System.out.println("------------------------------------");
            System.out.println("Olá Cliente PEDS, como podemos te ajudar?");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito");
            System.out.println("4 - Consulta");
            System.out.println("5 - Extrato");
            System.out.println("8 - Remover Conta Corrente");
            System.out.println("9 - Sair");
            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            if (opcao == 9) {
                System.out.println("Sistema encerrado.");
                break;
            }

            switch (opcao) {
                case 1 -> execCadastramento(sc);
                case 2 -> execSaque(sc);
                case 3 -> execDeposito(sc);
                case 4 -> execConsulta(sc);
                case 5 -> execExtrato(sc);
                case 8 -> execRemoverConta(sc);
                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }

    private static void execCadastramento(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();
        sc.nextLine(); // Consumir quebra de linha
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Saldo inicial: ");
        String saldoStr = sc.next();

        try {
            double saldo = Double.parseDouble(saldoStr.replace(",", "."));
            Lab03ContaCorrente conta;

            if (numAge >= 5000) { // Conta Especial
                System.out.print("Limite de crédito: ");
                String limiteStr = sc.next();
                double limite = Double.parseDouble(limiteStr.replace(",", "."));
                conta = new Lab05ContaCorrenteEspecial(numAge, numConta, nome, saldo, limite);
            } else { // Conta Comum
                conta = new Lab03ContaCorrente(numAge, numConta, nome, saldo);
            }

            conta.gravar();
            System.out.println("Conta cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Certifique-se de usar vírgula ou ponto.");
        }
    }

    private static void execSaque(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);
        System.out.print("Valor do saque: ");
        String valorStr = sc.next();

        try {
            double valor = Double.parseDouble(valorStr.replace(",", "."));
            if (valor <= 0) {
                System.out.println("Valor inválido! O saque deve ser positivo.");
                return;
            }

            if (conta.sacar(valor) == 1) {
                conta.gravar();
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Certifique-se de usar vírgula ou ponto.");
        }
    }

    private static void execDeposito(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);
        System.out.print("Valor do depósito: ");
        String valorStr = sc.next();

        try {
            double valor = Double.parseDouble(valorStr.replace(",", "."));
            if (valor <= 0) {
                System.out.println("Valor inválido! O depósito deve ser positivo.");
                return;
            }

            conta.depositar(valor);
            conta.gravar();
            System.out.println("Depósito realizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Certifique-se de usar vírgula ou ponto.");
        }
    }

    private static void execConsulta(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);
        conta.imprimir();
    }

    private static void execExtrato(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();

        Lab04Historico historico = new Lab04Historico(numAge, numConta);
        historico.imprimir();
    }

    private static void execRemoverConta(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);

        if (conta.removerArquivo()) {
            System.out.println("Conta removida com sucesso!");
        } else {
            System.out.println("Erro ao remover a conta.");
        }
    }

    private static Lab03ContaCorrente criarConta(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();

        if (numAge >= 5000) {
            return new Lab05ContaCorrenteEspecial(numAge, numConta);
        } else {
            return new Lab03ContaCorrente(numAge, numConta);
        }
    }
}