package view;

import model.Lab02ContaCorrente;
import java.util.Scanner;

public class Lab02Sistema {
    private static Lab02ContaCorrente contaCorrente;

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
            System.out.println("4 - Consulta");
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
                case 4:
                    execConsulta();
                    break;
                case 9:
                    continuar = false;
                    System.out.println("Sistema Encerrado.");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
                    break;
            }
        }

        sc.close();
    }

    public static void execCadastramento() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Número da Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Número da Conta: ");
        int numConta = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Nome do Cliente: ");
        String nome = sc.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Favor, confirme o cadastramento (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            contaCorrente = new Lab02ContaCorrente(numAge, numConta, nome, saldo);
            System.out.println("Cadastro realizado com sucesso.");
        } else {
            System.out.println("Cadastro cancelado.");
        }
    }

    public static void execSaque() {
        if (contaCorrente == null) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Valor do Saque: ");
        double valorSaque;
        do {
            valorSaque = sc.nextDouble();
            if (valorSaque <= 0.0) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (valorSaque <= 0.0);

        sc.nextLine(); // Consumir quebra de linha
        System.out.print("Confirmar o saque (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (contaCorrente.saque(valorSaque) == 1) {
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Que pena. Saldo insuficiente.");
            }
        } else {
            System.out.println("Saque cancelado.");
        }
    }

    public static void execDeposito() {
        if (contaCorrente == null) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Valor do Depósito: ");
        double valorDeposito;
        do {
            valorDeposito = sc.nextDouble();
            if (valorDeposito <= 0.0) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (valorDeposito <= 0.0);

        sc.nextLine(); // Consumir quebra de linha
        System.out.print("Confirma depósito (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            contaCorrente.deposito(valorDeposito);
            System.out.println("Depósito realizado.");
        } else {
            System.out.println("Depósito cancelado.");
        }
    }

    public static void execConsulta() {
        if (contaCorrente == null) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        contaCorrente.imprimir();
    }
}