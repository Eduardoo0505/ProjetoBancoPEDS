package view;

import model.Lab08ContaCorrente;
import model.Lab08ContaCorrenteEspecial;
import model.Lab08Historico;
import util.MyClassException;
import java.util.Scanner;

public class Lab08Sistema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
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
        } catch (MyClassException e) {
            System.out.println("Classe: " + e.getClasse());
            System.out.println("Mensagem Objeto: " + e.getMessage());
            System.out.println("Mensagem Negócio: " + e.getMensagem());
            System.out.println("Método: " + e.getMetodo());
        } finally {
            sc.close();
        }
    }

    private static void execCadastramento(Scanner sc) throws MyClassException {
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
            if (numAge >= 5000) {
                System.out.print("Limite de crédito: ");
                String limiteStr = sc.next();
                double limite = Double.parseDouble(limiteStr.replace(",", "."));
                Lab08ContaCorrenteEspecial conta = new Lab08ContaCorrenteEspecial(numAge, numConta, nome, saldo, limite);
                conta.gravar();
            } else {
                Lab08ContaCorrente conta = new Lab08ContaCorrente(numAge, numConta, nome, saldo);
                conta.gravar();
            }
            System.out.println("Conta cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            throw new MyClassException("Erro de formato nos valores fornecidos.", "Lab08Sistema", "execCadastramento");
        }
    }

    private static void execSaque(Scanner sc) throws MyClassException {
        Lab08ContaCorrente conta = criarConta(sc);
        System.out.print("Valor do saque: ");
        double valor = sc.nextDouble();
        conta.sacar(valor);
    }

    private static void execDeposito(Scanner sc) throws MyClassException {
        Lab08ContaCorrente conta = criarConta(sc);
        System.out.print("Valor do depósito: ");
        double valor = sc.nextDouble();
        conta.depositar(valor);
    }

    private static void execConsulta(Scanner sc) throws MyClassException {
        Lab08ContaCorrente conta = criarConta(sc);
        conta.imprimir();
    }

    private static void execExtrato(Scanner sc) throws MyClassException {
        Lab08ContaCorrente conta = criarConta(sc);
        Lab08Historico historico = new Lab08Historico(conta.getNumAge(), conta.getNumConta());
        historico.imprimir();
    }

    private static void execAtualizarSaldo(Scanner sc) throws MyClassException {
        Lab08ContaCorrente conta = criarConta(sc);

        if (conta instanceof Lab08ContaCorrenteEspecial) {
            Lab08ContaCorrenteEspecial contaEspecial = (Lab08ContaCorrenteEspecial) conta;
            contaEspecial.calcularJuros();
            contaEspecial.gravar();

            Lab08Historico historico = new Lab08Historico(contaEspecial.getNumAge(), contaEspecial.getNumConta());
            historico.gravar(3, contaEspecial.getSaldo());
        } else {
            throw new MyClassException("Conta não é remunerada.", "Lab08Sistema", "execAtualizarSaldo");
        }
    }

    private static void execRemoverConta(Scanner sc) throws MyClassException {
        Lab08ContaCorrente conta = criarConta(sc);
        conta.removerArquivo();
    }

    private static Lab08ContaCorrente criarConta(Scanner sc) throws MyClassException {
        System.out.print("Agência: ");
        int numAge = sc.nextInt();
        System.out.print("Conta: ");
        int numConta = sc.nextInt();

        if (numAge >= 5000) {
            return new Lab08ContaCorrenteEspecial(numAge, numConta);
        } else {
            return new Lab08ContaCorrente(numAge, numConta);
        }
    }
}