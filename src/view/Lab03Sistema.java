package view;

import model.Lab03ContaCorrente;
import java.util.Scanner;

public class Lab03Sistema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("BEM-VINDO AO SEU BANCO PEDS");
            System.out.println("------------------------------------");
            System.out.println("Olá Cliente PEDS, como podemos te ajudar?");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Deposito");
            System.out.println("4 - Consulta");
            System.out.println("9 - Sair");
            System.out.print("Opcao: ");
            int opcao = sc.nextInt();

            if (opcao == 9) {
                System.out.println("Saindo...");
                break;
            }

            switch (opcao) {
                case 1 -> execCadastramento(sc);
                case 2 -> execSaque(sc);
                case 3 -> execDeposito(sc);
                case 4 -> execConsulta(sc);
                default -> System.out.println("Opção inválida!");
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
        double saldo = sc.nextDouble();

        Lab03ContaCorrente conta = new Lab03ContaCorrente(numAge, numConta, nome, saldo);
        conta.gravar();
        System.out.println("Conta cadastrada com sucesso!");
    }

    private static void execSaque(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();
        Lab03ContaCorrente conta = new Lab03ContaCorrente(numAge, numConta);

        System.out.print("Valor do saque: ");
        double valor = sc.nextDouble();
        if (conta.sacar(valor) == 1) {
            conta.gravar();
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    private static void execDeposito(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();
        Lab03ContaCorrente conta = new Lab03ContaCorrente(numAge, numConta);

        System.out.print("Valor do depósito: ");
        double valor = sc.nextDouble();
        conta.depositar(valor);
        conta.gravar();
        System.out.println("Depósito realizado com sucesso!");
    }

    private static void execConsulta(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();
        Lab03ContaCorrente conta = new Lab03ContaCorrente(numAge, numConta);

        conta.imprimir();
    }
}