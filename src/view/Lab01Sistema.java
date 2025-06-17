package view;

import java.util.Scanner;

public class Lab01Sistema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("BEM-VINDO AO SEU BANCO PEDS");
            System.out.println("------------------------------------");
            System.out.println("Olá Cliente PEDS, como podemos te ajudar?");
            System.out.println("1 - Cadastramento");
            System.out.println("2 - Saque");
            System.out.println("3 - Deposito");
            System.out.println("9 - Fim");
            System.out.print("Opcao: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    execCadastramento();
                    break;
                case 2:
                    execSaque();
                    break;
                case 3:
                    execDeposito();
                    break;
                case 9:
                    continuar = false;
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }

        sc.close();
    }

    public static void execCadastramento() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Numero da Agencia: ");
        int numAgencia = sc.nextInt();
        System.out.print("Numero da Conta: ");
        int numConta = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Nome do Cliente: ");
        String nomeCliente = sc.nextLine();
        System.out.print("Saldo: ");
        double saldo = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Confirma cadastramento (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Cadastramento realizado com sucesso.");
        } else {
            System.out.println("Cadastramento cancelado.");
        }
    }

    public static void execSaque() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Numero da Agencia: ");
        int numAgencia = sc.nextInt();
        System.out.print("Numero da Conta: ");
        int numConta = sc.nextInt();
        System.out.print("Valor do Saque: ");
        double valorSaque = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Confirma saque (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Saque realizado.");
        } else {
            System.out.println("Saque cancelado.");
        }
    }

    public static void execDeposito() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Numero da Agencia: ");
        int numAgencia = sc.nextInt();
        System.out.print("Numero da Conta: ");
        int numConta = sc.nextInt();
        System.out.print("Valor do Deposito: ");
        double valorDeposito = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Confirma deposito (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Deposito realizado.");
        } else {
            System.out.println("Deposito cancelado.");
        }
    }
}