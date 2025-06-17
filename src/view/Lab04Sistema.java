package view;

import model.Lab03ContaCorrente;
import model.Lab04Historico;

import java.io.File;
import java.util.Scanner;

public class Lab04Sistema {
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
            System.out.println("5 - Extrato");
            System.out.println("8 - Remover Conta Corrente");
            System.out.println("9 - Sair");
            System.out.print("Opcao: ");
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

    private static int[] obterDadosConta(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();
        return new int[]{numAge, numConta};
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
            Lab03ContaCorrente conta = new Lab03ContaCorrente(numAge, numConta, nome, saldo);
            conta.gravar();
            System.out.println("Conta cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Saldo inicial inválido! Certifique-se de usar vírgula ou ponto.");
        }
    }

    private static void execSaque(Scanner sc) {
        int[] dados = obterDadosConta(sc);
        Lab03ContaCorrente conta = new Lab03ContaCorrente(dados[0], dados[1]);
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
                Lab04Historico historico = new Lab04Historico(dados[0], dados[1]);
                historico.gravar(1, valor);
                System.out.println("Saque realizado com sucesso!");
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor de saque inválido! Certifique-se de usar vírgula ou ponto.");
        }
    }

    private static void execDeposito(Scanner sc) {
        int[] dados = obterDadosConta(sc);
        Lab03ContaCorrente conta = new Lab03ContaCorrente(dados[0], dados[1]);
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
            Lab04Historico historico = new Lab04Historico(dados[0], dados[1]);
            historico.gravar(2, valor);
            System.out.println("Depósito realizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Valor de depósito inválido! Certifique-se de usar vírgula ou ponto.");
        }
    }

    private static void execConsulta(Scanner sc) {
        int[] dados = obterDadosConta(sc);
        Lab03ContaCorrente conta = new Lab03ContaCorrente(dados[0], dados[1]);
        conta.imprimir();
    }

    private static void execExtrato(Scanner sc) {
        int[] dados = obterDadosConta(sc);
        Lab04Historico historico = new Lab04Historico(dados[0], dados[1]);
        historico.imprimir();
    }

    private static void execRemoverConta(Scanner sc) {
        int[] dados = obterDadosConta(sc);
        Lab03ContaCorrente conta = new Lab03ContaCorrente(dados[0], dados[1]);
        Lab04Historico historico = new Lab04Historico(dados[0], dados[1]);

        boolean contaRemovida = conta.removerArquivo();
        File arquivoHistorico = new File(dados[0] + "." + dados[1] + ".hist");
        boolean historicoRemovido = arquivoHistorico.exists() && arquivoHistorico.delete();

        if (contaRemovida && historicoRemovido) {
            System.out.println("Conta e histórico removidos com sucesso.");
        } else {
            System.out.println("Erro ao remover a conta ou o histórico.");
        }
    }
}