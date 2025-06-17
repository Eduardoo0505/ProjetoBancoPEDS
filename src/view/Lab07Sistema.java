package view;

import model.Lab03ContaCorrente;
import model.Lab07ContaRemunerada;
import model.Lab07Historico;
import java.util.Scanner;

public class Lab07Sistema {
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
            System.out.println("6 - Atualizar Saldo");
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
                case 6 -> execAtualizarSaldo(sc);
                case 8 -> execRemoverConta(sc);
                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }

    private static Lab03ContaCorrente criarConta(Scanner sc) {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();

        return (numAge >= 5000)
            ? new Lab07ContaRemunerada(numAge, numConta)
            : new Lab03ContaCorrente(numAge, numConta);
    }

    private static void execAtualizarSaldo(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);

        if (conta instanceof Lab07ContaRemunerada) {
            Lab07ContaRemunerada contaRemunerada = (Lab07ContaRemunerada) conta;
            System.out.printf("Saldo antes da atualização: R$ %.2f%n", contaRemunerada.getSaldo());
            contaRemunerada.calcularJuros();
            contaRemunerada.gravar();
            System.out.printf("Saldo após a atualização: R$ %.2f%n", contaRemunerada.getSaldo());

            Lab07Historico historico = new Lab07Historico(conta.getNumAge(), conta.getNumConta());
            historico.gravar(3, contaRemunerada.getSaldo());
        } else {
            System.out.println("Essa conta não é uma conta remunerada. Operação não realizada.");
        }
    }

    private static void execExtrato(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);
        Lab07Historico historico = new Lab07Historico(conta.getNumAge(), conta.getNumConta());
        historico.imprimir();
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

            if (numAge >= 5000) {
                System.out.print("Limite de crédito: ");
                String limiteStr = sc.next();
                double limite = Double.parseDouble(limiteStr.replace(",", "."));
                conta = new Lab07ContaRemunerada(numAge, numConta, nome, saldo, limite);
            } else {
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

    private static void execRemoverConta(Scanner sc) {
        Lab03ContaCorrente conta = criarConta(sc);

        if (conta.removerArquivo()) {
            System.out.println("Conta removida com sucesso!");
        } else {
            System.out.println("Erro ao remover a conta.");
        }
    }
}